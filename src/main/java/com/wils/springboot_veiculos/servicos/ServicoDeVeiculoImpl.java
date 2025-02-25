package com.wils.springboot_veiculos.servicos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wils.springboot_veiculos.dtos.DtoDeCriacaoDeVeiculo;
import com.wils.springboot_veiculos.dtos.DtoDeVeiculo;
import com.wils.springboot_veiculos.modelos.Veiculo;
import com.wils.springboot_veiculos.repositorios.RepositorioDeVeiculo;

@Service
public class ServicoDeVeiculoImpl implements ServicoDeVeiculo {
  @Autowired
  RepositorioDeVeiculo repositorio;

  @Override
  public DtoDeVeiculo criarVeiculo(DtoDeCriacaoDeVeiculo dto) {
      Veiculo veiculo = repositorio.save(converterParaEntidade(dto));
      return converterParaDto(veiculo);
  }

  @Override
  public List<DtoDeVeiculo> listarVeiculos() {
    return repositorio.findAll().stream().map(v -> converterParaDto(v)).toList();
  }

  @Override
  public Optional<DtoDeVeiculo> consultarVeiculoPorId(UUID id) {
    var encontrouVeiculo = repositorio.findById(id);
    if (encontrouVeiculo.isEmpty())
      return null;
    else
      return Optional.of(converterParaDto(encontrouVeiculo.get()));
  }

  @Override
  public Optional<DtoDeVeiculo> alterarVeiculo(UUID id, DtoDeVeiculo dto) {
    var encontrouVeiculo = repositorio.findById(id);
    if (encontrouVeiculo.isEmpty())
      return null;
    else {
      Veiculo veiculo = encontrouVeiculo.get();
      BeanUtils.copyProperties(dto, veiculo);
      return Optional.of(converterParaDto(repositorio.save(veiculo)));
    }
  }

  @Override
  public Boolean excluirVeiculo(UUID id) {
    var encontrouVeiculo = repositorio.findById(id);
    if (encontrouVeiculo.isEmpty())
      return false;
    else {
      repositorio.deleteById(id);
      return true;
    }
  }

  //conversores
  private DtoDeVeiculo converterParaDto(Veiculo veiculo) {
      return new DtoDeVeiculo(veiculo.getId(), veiculo.getNome(), veiculo.getMarca(), veiculo.getModelo(),
      veiculo.getCor(), veiculo.getAno(), veiculo.getPlaca(), veiculo.getRenavam());
  }

  private Veiculo converterParaEntidade(DtoDeCriacaoDeVeiculo dtoDeVeiculo) {
      Veiculo veiculo = new Veiculo();
      BeanUtils.copyProperties(dtoDeVeiculo, veiculo);
      return veiculo;
  }
}
