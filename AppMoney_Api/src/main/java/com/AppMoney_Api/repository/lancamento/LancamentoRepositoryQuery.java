package com.AppMoney_Api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.AppMoney_Api.model.Lancamento;
import com.AppMoney_Api.repository.filter.LancamentoFilter;



// A classe precisa receber o nome inicial exatamente igual à classe do repositorio
// Neste caso a classe do repositorio vai extender essa classe, dessa forma o JPA entende que pode usar os métodos que eu criei como um dos próprios metodos
public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
