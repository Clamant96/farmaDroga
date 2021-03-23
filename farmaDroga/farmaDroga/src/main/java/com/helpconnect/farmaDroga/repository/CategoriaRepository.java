package com.helpconnect.farmaDroga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpconnect.farmaDroga.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	public List<Categoria> findAllByNomeContainingIgnoreCase(String nome);
	
	public List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao);

}
