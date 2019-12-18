package com.AppMoney_Api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.AppMoney_Api.model.Lancamento;
import com.AppMoney_Api.model.Lancamento_;
import com.AppMoney_Api.repository.filter.LancamentoFilter;

// A classe precisa receber exatamente o primeiro nome como nome do repositório
// A classe irá implementar os métodos da interface Query
public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager; // gerenciamento para poder trabalhar com a consulta

	@Override
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class); // cira a query a partir de qual
																					// dado(lancamento)
		Root<Lancamento> root = criteria.from(Lancamento.class); // pega os atributos de Lancamento com os quais eu //
																	// Em que estou fazendo a consulta
																	// quero fazer os filtros

		// criar restrições
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root); // Adiciona o filtro em uma array de
																					// predicados com todos os
																					// predicados que compoe minhas
																					// regras passsando o lancamentoFilter, o builder e o root
		criteria.where(predicates);

		TypedQuery<Lancamento> query = manager.createQuery(criteria);

		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {

			predicates.add(builder.like(builder.lower(root.get(Lancamento_.descricao)),
					"%" + lancamentoFilter.getDescricao().toLowerCase() + "%")); // o símbolo "%" para ele poder fazer a
																					// pesquisa em qualuqer lugar
		}

		if (!StringUtils.isEmpty(lancamentoFilter.getDataVencimentoDe())) {

			predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento),
					lancamentoFilter.getDataVencimentoDe()));
		}

		if (!StringUtils.isEmpty(lancamentoFilter.getDataVencimentoAte())) {

			predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento),
					lancamentoFilter.getDataVencimentoAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Lancamento> query, Pageable pageable) {

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Long total(LancamentoFilter lancamentoFilter) {

		// Necessito criar uma nova builder para captura a busca pelo tamanhao da query
		// ou o total de dados que a minha busca retorna
		// Isso seria fazer um novo filtro na pesquisa de lancamento

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));     //Conta quantos registro existem dentro da origem(root)

		return manager.createQuery(criteria).getSingleResult();  //retorna a quantidade de registros
	}
}
