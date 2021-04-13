package com.helpconnect.farmaDroga.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpconnect.farmaDroga.model.Produto;
import com.helpconnect.farmaDroga.model.Usuario;
import com.helpconnect.farmaDroga.repository.ProdutoRepository;
import com.helpconnect.farmaDroga.repository.UsuarioRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoReponsitory;
	
	public Produto cadastroUsuarioProduto(long idProduto, long idUsuario) {
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
		Optional<Produto> produtoExistente = produtoReponsitory.findById(idProduto);
		
		if(usuarioExistente.isPresent() && produtoExistente.isPresent()) {
			produtoExistente.get().getUsuarios().add(usuarioExistente.get());
			
			produtoReponsitory.save(produtoExistente.get());
			
			this.gerenciarEstoque(idProduto/*, idUsuario*/);
			
			return produtoReponsitory.save(produtoExistente.get());
			
		}
		
		return null;
	
	}
	
	public void gerenciarEstoque(long idProduto/*, long idUsuario*/) {
		//Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
		Optional<Produto> produtoExistente = produtoReponsitory.findById(idProduto);
		
		produtoExistente.get().setQtdProduto(produtoExistente.get().getQtdProduto() - 1);
		
		/*if(!(usuarioExistente.get().getProdutos().isEmpty())) {
			produtoExistente.get().setQtdProduto(produtoExistente.get().getQtdProduto() - 1);
			
			produtoReponsitory.save(produtoExistente.get());
			
			//return produtoReponsitory.save(produtoExistente.get());
		}*/
		
		//return null;
	}

}
