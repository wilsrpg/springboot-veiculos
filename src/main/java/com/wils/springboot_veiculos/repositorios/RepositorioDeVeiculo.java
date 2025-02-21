package com.wils.springboot_veiculos.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wils.springboot_veiculos.modelos.Veiculo;

@Repository
public interface RepositorioDeVeiculo extends JpaRepository<Veiculo, UUID> {
}