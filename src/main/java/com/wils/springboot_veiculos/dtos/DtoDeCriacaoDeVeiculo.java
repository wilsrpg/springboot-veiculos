package com.wils.springboot_veiculos.dtos;

import com.wils.springboot_veiculos.constraint.AnoAteHoje;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;

public record DtoDeCriacaoDeVeiculo(
    @NotBlank(message = "O campo 'nome' não pode estar vazio.")
    String nome,

    @NotBlank(message = "O campo 'marca' não pode estar vazio.")
    String marca,

    @NotBlank(message = "O campo 'modelo' não pode estar vazio.")
    String modelo,

    @NotNull(message = "O campo 'cor' não pode estar vazio.")
    @Min(value = 1, message = "O código da cor é um número entre 1 e 16: "
      + "1-Amarelo, 2-Azul, 3-Bege, 4-Branco, 5-Cinza, 6-Dourado, 7-Grená, 8-Laranja, "
      + "9-Marrom, 10-Prata, 11-Preto, 12-Rosa, 13-Roxo, 14-Verde, 15-Vermelho, 16-Fantasia."
    )
    @Max(value = 16, message = "O código da cor é um número entre 1 e 16: "
      + "1-Amarelo, 2-Azul, 3-Bege, 4-Branco, 5-Cinza, 6-Dourado, 7-Grená, 8-Laranja, "
      + "9-Marrom, 10-Prata, 11-Preto, 12-Rosa, 13-Roxo, 14-Verde, 15-Vermelho, 16-Fantasia."
    )
    Integer cor,

    @NotNull(message = "O campo 'ano' não pode estar vazio.")
    //@Pattern(regexp = "^(19|20)[0-9]{2}$", message = "O ano deve ser maior que 1900 e menor ou igual ao ano atual.")
    //@Size(min = 4, max = 4, message = "O ano deve ser maior que 1900 e menor ou igual ao ano atual.")
    //@Min(value = 1900, message = "O ano deve ser maior que 1900 e menor ou igual ao ano atual.")
    //@Max(value = 2025, message = "O ano deve ser maior que 1900 e menor ou igual ao ano atual.")
    //@PastOrPresent(message = "O ano deve ser maior que 1900 e menor ou igual ao ano atual.")
    @AnoAteHoje(min = 1886)
    Integer ano,

    @NotBlank(message = "O campo 'placa' não pode estar vazio.")
    @Pattern(regexp = "^[a-zA-Z]{3}[0-9][a-zA-Z0-9][0-9]{2}$",
      message = "A placa deve conter apenas letras e números "
              + "e seu formato deve ser 'LLLNLNN' ou 'LLLNNNN'.")
    String placa,

    @NotBlank(message = "O campo 'renavam' não pode estar vazio.")
    @Pattern(regexp = "^[0-9]{11}$", message = "O RENAVAM deve conter 11 números.")
    String renavam
  ) {
}