package com.helpconnect.farmaDroga.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpconnect.farmaDroga.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{ //Long => id
	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
	
	@Query(value = "SELECT * FROM usuario WHERE ativa = :ativa", nativeQuery = true)
	public List<Usuario> findAllByAtiva(@Param("ativa") boolean ativa);
	
	//@Query(value = "SELECT * FROM farma_droga.usuario INNER JOIN farma_droga.produto_usuario ON farma_droga.usuario.id = farma_droga.produto_usuario.usuario_id INNER JOIN farma_droga.produto ON farma_droga.produto.id = farma_droga.produto_usuario.produto_id WHERE farma_droga.usuario.id = :id", nativeQuery = true)
	//@Query(value = "SELECT * FROM farma_droga.usuario INNER JOIN farma_droga.produto_usuario ON farma_droga.usuario.id = farma_droga.produto_usuario.usuario_id WHERE farma_droga.usuario.id = :id", nativeQuery = true)
	@Query(value = "SELECT * FROM farma_droga.produto_usuario AS compras INNER JOIN farma_droga.usuario AS usuario ON compras.usuario_id = usuario.id WHERE compras.usuario_id = :id", nativeQuery = true)
	public List<Usuario> findAllById(@Param("id") long id);
	
	/* PESQUISE PELO NOME DE USUARIO */
	public Optional<Usuario> findByCpf(String cpf);
	
}
