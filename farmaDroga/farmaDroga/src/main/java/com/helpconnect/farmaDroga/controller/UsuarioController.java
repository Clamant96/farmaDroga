package com.helpconnect.farmaDroga.controller;

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

import com.helpconnect.farmaDroga.model.Usuario;
import com.helpconnect.farmaDroga.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*") // nao importa a origem da pesquisa, ele sempre ira pesquisar
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	/*PESQUISA TODOS USUARIOS CADASTROS EM NOSSA BASE DE DADOS*/
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	/*PESQUISA UM USUARIO POR MEIO DO ID*/
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable long id){
		
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	/*FUNCIONA COMO O LIKE DO MYSQL, VC INDICA LETRAS E ELE TE TRAZ TUDO QUE ELE ENCONTRAR COM ESSA REFERENCIA*/
	@GetMapping("nome/{nome}")
	public ResponseEntity<List<Usuario>> findByNomeUsuario(@PathVariable String nome){
		
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	/*INSERE UM NOVO DADO DENTRO DA TABELA USUARIO*/
	@PostMapping
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	/*ATUALIZA UM USUARIO JA CADASTRADO DENTRO DE NOSSA BASE DE DADOS*/
	@PutMapping
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario){
		
		return ResponseEntity.ok(repository.save(usuario));
	}
	
	/*DELETA UM USUARIO CADASTRADO EM NOSSA BASE DE DADOS POR MEIO DE SEUS ID*/
	@DeleteMapping("/{id}")
	public void deleteUsuario(@PathVariable long id) {
		
		repository.deleteById(id);
	}
}
