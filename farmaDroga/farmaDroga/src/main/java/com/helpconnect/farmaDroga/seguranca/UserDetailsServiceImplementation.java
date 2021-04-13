package com.helpconnect.farmaDroga.seguranca;

import java.util.Optional;

import com.helpconnect.farmaDroga.model.Usuario;
import com.helpconnect.farmaDroga.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		/* PARA VALIDAR O LOGIN, VERIFICAMOS SE O USUARIO CADASTRADOS SE ENCONTRA EM NOSSA BASE DE DADOS */
		Optional<Usuario> user = userRepository.findByCpf(userName);
		
		/* CASE DE ERRO, INFORMAMOS O USUARIO INSERIDO E INFORMAMOS QUE O MESMO NAO EXISTE EM NOSSA BASE DE DADOS */
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		
		/* CASO O USUARIO ESTAJA CADASTRADO EM NOSSA BASE DE DADOS, RETORNAMOS O LOGIN BEM SUCEDIDO DO USUARIO */
		return user.map(UserDetailsImplementation::new).get();
	}

}
