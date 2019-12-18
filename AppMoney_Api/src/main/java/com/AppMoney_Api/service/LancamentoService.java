package com.AppMoney_Api.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.AppMoney_Api.model.Lancamento;
import com.AppMoney_Api.model.Pessoa;
import com.AppMoney_Api.repository.LancamentoRepository;
import com.AppMoney_Api.repository.PessoaRepository;
import com.AppMoney_Api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Lancamento salvar(Lancamento lancamento) {

		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo())
				.orElseThrow(() -> new PessoaInexistenteOuInativaException());

		if (pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}

		return lancamentoRepository.save(lancamento);
	}

}
