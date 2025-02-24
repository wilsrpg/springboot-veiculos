package com.wils.springboot_veiculos.controladores;
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

import com.wils.springboot_veiculos.dtos.DtoDeVeiculo;
import com.wils.springboot_veiculos.servicos.ServicoDeVeiculo;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class ControladorDeVeiculo {

  @Autowired
  ServicoDeVeiculo servico;

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
    return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
  }

  @GetMapping("/veiculos")
  public List<DtoDeVeiculo> listarVeiculos() {
    return servico.listarVeiculos();
  }

  @GetMapping("/veiculo/{id}")
  public ResponseEntity<DtoDeVeiculo> consultarVeiculoPorId(@PathVariable UUID id) {
    Optional<DtoDeVeiculo> veiculo = servico.consultarVeiculoPorId(id);
    if (veiculo.isEmpty())
      return ResponseEntity.notFound().build();
    else
      return ResponseEntity.status(HttpStatus.OK).body(veiculo.get());
  }

  @PutMapping("/veiculo/{id}")
  public ResponseEntity<DtoDeVeiculo> alterarVeiculo(@PathVariable UUID id, @RequestBody DtoDeVeiculo dtoDeVeiculo) {
    Optional<DtoDeVeiculo> veiculo = servico.alterarVeiculo(id, dtoDeVeiculo);
    if (veiculo.isEmpty())
      return ResponseEntity.notFound().build();
    else
      return ResponseEntity.status(HttpStatus.OK).body(veiculo.get());
  }

  @DeleteMapping("/veiculo/{id}")
  public ResponseEntity<String> deleteVeiculo(@PathVariable UUID id) {
    Boolean excluiu = servico.excluirVeiculo(id);
    if (!excluiu)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado.");
    else
      return ResponseEntity.status(HttpStatus.OK).body("Veículo deletado com sucesso.");
  }
}