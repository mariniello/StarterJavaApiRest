package com.AppMoney_Api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AppMoney_Api.event.RecursoCriadoEvent;
import com.AppMoney_Api.model.Categoria;
import com.AppMoney_Api.repository.CategoriaRepository;


// A classe expõe tudo relacionado com os recursos da classe Categorias
// Recebe requisições (Postman, Insomnia)


@RestController   									// Jà configura o retorno para Json
@RequestMapping("/categorias")						// Mapeamento da requisição
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository; /* Somente esta implementação de CategoriaRepository vai dar um erro de 
													 nullExcetion. Seria necessário inicializar a interface, mas isso não pode
													 ser feito aqui. Por isso através do @Autowrided, o Spring vai buscar uma 
													 implementação de CategoriaRepository e entrega para a função.*/
	
	@Autowired
	private ApplicationEventPublisher publisher;	// Publicação do evento
	
	
	@GetMapping										//Mapeamento do GET para este método
	public List<Categoria> listar(){				// v. ResponseEntity<?> // retornando respostas 
		
		
		return categoriaRepository.findAll();   	// findAll(); " Implementa SELECT * FROM categorias " do repositório Categoria
		
	
	// Mudando a forma de return para gerar resposta e retornar erro quando a lista estiver vazia
		
		
//		List<Categoria> categorias = categoriaRepository.findAll();
//		
//		return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.notFound().build();
//		
				
	}
	
	@PostMapping									// Mapeia o método POST para esta função
	//@ResponseStatus(HttpStatus.CREATED)				// Retorna um status de criação para a requisição
														// Uso HttpServletResponse para trabahar com o Header e devolver o location para 
														// o método post (isso é uma regra do Rest)
															
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {   
		
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
//														// fazendo uma construção, a partir da requisição atual, passando o caminho, com o código da categoria salva 
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();
//														// Setar o header Location com esta uri
//		response.setHeader("Location",	uri.toASCIIString());
		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);  // Já não preciso da anotação de CREATED, pois aqui já estou dizendo o status
	}																// e retornando categoria salva
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
		
		Optional<Categoria> categoria = this.categoriaRepository.findById(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
		
	}
	
	
}









//
//1. Obter por código
//findOne
//Na versão atual do Spring Data, o método findOne não é compatível mais com o que foi mostrado na aula. Na aula este método recebia apenas o código e retornava o recurso de acordo com este código.
//
//Caso queira usar ainda este método, é possível, porém precisará criar um “exemplo” do recurso para obtê-lo.
//
//Como assim?
//			@GetMapping("/{codigo}")
//			public Categoria buscarPeloCodigo(@PathVariable Long codigo) {
//    				Categoria categoriaExample = new Categoria();
//   				categoriaExample.setCodigo(codigo);
//    
//    				Example example = Example.of(categoriaExample);
//    
//    				return this.categoriaRepository.findOne(example).orElse(null);
//					}

//O que fizemos?
//Criamos uma instância de Categoria, atribuindo a ela os valores que queremos obter na consulta findOne.
//Atribuímos essa instância de categoria para o método of da interface org.springframework.data.domain.Example
//Chamamos o método findOne passando o Example criado.
//findById
//Apesar de ainda podermos utilizar o método findOne, ele se tornou trabalhoso para este caso, já que tudo que queremos, é obter o recurso através de seu código.
//
//Para isso, temos o método findById, que faz exatamente o que queremos!
//
//				@GetMapping("/{codigo}")
//				public Categoria buscarPeloCodigo(@PathVariable Long codigo) {
//  					return this.categoriaRepository.findById(codigo).orElse(null);
//}
//Lembrando que este método também retorna um Optional sendo necessário o uso de métodos como o get() ou orElse(...)
//
//2. Observações
//Para o caso que vimos nesta aula, o ideal seria utilizarmos o método findById por deixar o código mais limpo, mas a consulta gerada será a mesma.
//
//É importante lembrar que para o método findOne, você não precisa passar apenas o código, mas pode criar um exemplo com um ou mais campos da sua entidade, contanto que na sua base de dados não exista mais que um registro que contemple esse exemplo.
//
//Vale lembrar também que o retorno deste método é um Optional, portanto é necessário utilizar métodos como get() ou orElse(...)
