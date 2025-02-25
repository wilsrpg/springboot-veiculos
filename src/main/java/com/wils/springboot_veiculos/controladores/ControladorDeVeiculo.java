package com.wils.springboot_veiculos.controladores;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wils.springboot_veiculos.dtos.DtoDeVeiculo;
import com.wils.springboot_veiculos.servicos.ServicoDeFila;
import com.wils.springboot_veiculos.servicos.ServicoDeVeiculo;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class ControladorDeVeiculo {

  @Autowired
  ServicoDeVeiculo servico;

	@Autowired
	private ServicoDeFila fila;

  @PostMapping("/veiculos")
  public ResponseEntity<Object> criarVeiculo(@RequestBody @Valid DtoDeVeiculo dtoDeVeiculo,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      class Erros {
        @SuppressWarnings("unused")
        public Object[] arrayDeErros;
        public Erros(Object[] e){arrayDeErros = e;};
      };
      Erros erros = new Erros(bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).toArray());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
    DtoDeVeiculo veiculo = servico.criarVeiculo(dtoDeVeiculo);
    fila.sendMessage("Novo veículo cadastrado: " + veiculo.nome());
    return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
  }

  @GetMapping("/veiculos")
  public List<DtoDeVeiculo> listarVeiculos() {
    return servico.listarVeiculos();
  }

  @GetMapping("/veiculo/{id}")
  public ResponseEntity<Object> consultarVeiculoPorId(@PathVariable UUID id) {
    Optional<DtoDeVeiculo> veiculo = servico.consultarVeiculoPorId(id);
    if (veiculo == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        new Erro("Veículo não encontrado.").paraJson()
      );
    else
      return ResponseEntity.status(HttpStatus.OK).body(veiculo.get());
  }

  @PutMapping("/veiculo/{id}")
  public ResponseEntity<Object> alterarVeiculo(@PathVariable UUID id, @RequestBody DtoDeVeiculo dtoDeVeiculo) {
    Optional<DtoDeVeiculo> veiculo = servico.alterarVeiculo(id, dtoDeVeiculo);
    if (veiculo == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        new Erro("Veículo não encontrado.").paraJson()
      );
    else
      return ResponseEntity.status(HttpStatus.OK).body(veiculo.get());
  }

  @DeleteMapping("/veiculo/{id}")
  public ResponseEntity<Object> deleteVeiculo(@PathVariable UUID id) {
    Boolean excluiu = servico.excluirVeiculo(id);
    if (!excluiu)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        new Erro("Veículo não encontrado.").paraJson()
      );
    else
      return ResponseEntity.status(HttpStatus.OK).body(true);
  }
}


class Erro {
  Object erroObj;

  Erro(String error) {
    String e = error;
    this.erroObj = new Object(){@JsonSerialize String error = e;};
  }

  String paraJson() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonString = objectMapper.writeValueAsString(erroObj);
      return jsonString;
    } catch (JsonMappingException e) {
      e.printStackTrace();
      return "Erro ao serializar objeto.";
    } catch (JsonGenerationException e) {
      e.printStackTrace();
      return "Erro ao serializar objeto.";
    } catch (IOException e) {
      e.printStackTrace();
      return "Erro ao serializar objeto.";
    }
  }
}