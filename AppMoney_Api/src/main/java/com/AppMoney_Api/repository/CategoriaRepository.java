package com.AppMoney_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AppMoney_Api.model.Categoria;


// A interface CategoriaRepository extende a JpaRepository usando Categoria e sua chave primária
// Jpa disponibiliza vários métodos e operações de CRUD já estarão prontas para uso pelo Starter Data JPA
// Categoria seria um objeto genérico e sua chave primária para executar operações de busca

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	
}
