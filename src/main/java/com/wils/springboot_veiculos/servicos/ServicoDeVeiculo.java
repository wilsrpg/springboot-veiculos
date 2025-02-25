package com.wils.springboot_veiculos.servicos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.wils.springboot_veiculos.dtos.DtoDeCriacaoDeVeiculo;
import com.wils.springboot_veiculos.dtos.DtoDeVeiculo;

public interface ServicoDeVeiculo {
  public DtoDeVeiculo criarVeiculo(DtoDeCriacaoDeVeiculo dto);
  public List<DtoDeVeiculo> listarVeiculos();
  public Optional<DtoDeVeiculo> consultarVeiculoPorId(UUID id);
  public Optional<DtoDeVeiculo> alterarVeiculo(UUID id, DtoDeVeiculo dto);
  public Boolean excluirVeiculo(UUID id);
}
