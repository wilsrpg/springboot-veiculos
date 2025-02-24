package com.wils.springboot_veiculos.modelos;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Veiculo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @NotBlank private String nome;
  @NotBlank private String marca;
  @NotBlank private String modelo;
  @NotNull private Integer cor;
  @Transient
  private final String[] cCorDenatran = { "o índice deste array corresponde ao número da cor",
    "Amarelo", "Azul",    "Bege",     "Branco",
    "Cinza",   "Dourado", "Grená",    "Laranja",
    "Marrom",  "Prata",   "Preto",    "Rosa",
    "Roxo",    "Verde",   "Vermelho", "Fantasia"
  };
  @NotNull private Integer ano;
  @NotBlank private String placa;
  @NotBlank private String renavam;

  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }
  public void setNome(String n) {
    this.nome = n;
  }

  public String getMarca() {
    return this.marca;
  }
  public void setMarca(String n) {
    this.marca = n;
  }

  public String getModelo() {
    return this.modelo;
  }
  public void setModelo(String n) {
    this.modelo = n;
  }

  public String getPlaca() {
    return this.placa;
  }
  public void setPlaca(String n) {
    this.placa = n;
  }

  public String getRenavam() {
    return this.renavam;
  }
  public void setRenavam(String n) {
    this.renavam = n;
  }

  public Integer getCor() {
    return this.cor;
  }
  public void setCor(Integer n) {
    this.cor = n;
  }
  public String getCorComoString() {
    return this.cCorDenatran[cor];
  }

  public Integer getAno() {
    return this.ano;
  }
  public void setAno(Integer n) {
    this.ano = n;
  }
}