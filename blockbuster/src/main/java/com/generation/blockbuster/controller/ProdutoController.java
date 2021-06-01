package com.generation.blockbuster.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blockbuster.model.Produto;
import com.generation.blockbuster.repository.ProdutoRepository;

@RestController
@RequestMapping("/filmes")
@CrossOrigin(origins="*", allowedHeaders="*")
public class ProdutoController {

		@Autowired
		private ProdutoRepository repositorio;
		
		@GetMapping
		public ResponseEntity <List<Produto>> getAll(){
			return ResponseEntity.ok(repositorio.findAll());
		}
		

		@GetMapping("/{id}")
		public ResponseEntity <Produto> getById(@PathVariable long id){
			return repositorio.findById(id).map(resposta -> ResponseEntity.ok(resposta))
											.orElse(ResponseEntity.notFound().build());
		}
		
		@GetMapping("/titulo/{titulo}")
		public ResponseEntity <List<Produto>> getByTitulo(@PathVariable String titulo){
			return ResponseEntity.ok(repositorio.findAllByTituloContainingIgnoreCase(titulo));
		}
		
		@GetMapping("/valor/{valor}")
		public ResponseEntity<List<Produto>> getAllByValor (@PathVariable BigDecimal valor) {
			return ResponseEntity.ok(repositorio.findByValor(valor));
		}
		
		@GetMapping("/valor/<{valor}")
		public ResponseEntity<List<Produto>> getAllByValorLessEqual (@PathVariable BigDecimal valor) {
			return ResponseEntity.ok(repositorio.findByValorLessThanEqual(valor));
		}
		
		@GetMapping("/descricao/{descricao}")
		public ResponseEntity <List<Produto>> getByDescricao(@PathVariable String descricao){
			return ResponseEntity.ok(repositorio.findAllByDescricaoContainingIgnoreCase(descricao));
		}

		
		@PostMapping
		public ResponseEntity <Produto> posting (@RequestBody Produto produto) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(produto));
		}
		
		@PutMapping
		public ResponseEntity <Produto> putting (@RequestBody Produto produto) {
			return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(produto));
		}

		@DeleteMapping("/{id}")
		public void deleting (@PathVariable long id) {
			repositorio.deleteById(id);
		}
}
