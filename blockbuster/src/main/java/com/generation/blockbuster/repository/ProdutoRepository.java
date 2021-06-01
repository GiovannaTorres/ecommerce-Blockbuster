package com.generation.blockbuster.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blockbuster.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long>{

	public List<Produto> findAllByTituloContainingIgnoreCase(String titulo);

	public List<Produto> findByValor(BigDecimal valor);
	
	public List<Produto> findByValorLessThanEqual(BigDecimal valor);

	public List<Produto> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}
