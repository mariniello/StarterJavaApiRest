package com.AppMoney_Api.repository.lancamento;

import java.util.List;

import com.AppMoney_Api.model.Lancamento;
import com.AppMoney_Api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
