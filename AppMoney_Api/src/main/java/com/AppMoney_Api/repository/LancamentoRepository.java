package com.AppMoney_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AppMoney_Api.model.Lancamento;
import com.AppMoney_Api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
