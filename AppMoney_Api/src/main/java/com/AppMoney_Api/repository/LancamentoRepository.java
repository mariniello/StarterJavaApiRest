package com.AppMoney_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AppMoney_Api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
