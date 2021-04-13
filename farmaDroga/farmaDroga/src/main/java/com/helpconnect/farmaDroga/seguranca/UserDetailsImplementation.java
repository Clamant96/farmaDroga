package com.helpconnect.farmaDroga.seguranca;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.helpconnect.farmaDroga.model.Usuario;

public class UserDetailsImplementation implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private String cpf;
	private String password;
	
	/* LEMBRAR DE SEMPRE INSERIR ESSE ATRIBUTO, PARA QUE POSSAMOS TRAZER O SEU VALOR QUANDO FORMOS AUTENTICAR UM USUARIO PARA ACESSAR UMA DETERMIANDA PAGINA DA APLICACA */
	private List<GrantedAuthority> authorities; 

	public UserDetailsImplementation(Usuario user) {
		this.cpf = user.getCpf();
		this.password = user.getSenha();		
	}

	public UserDetailsImplementation() {
		
	}
	
	/* COMO ESTAMOS IMPLEMENTADNDO UMA CLASSE, DEVEMOS IMPORTAR TODOS OS SEUS METODOS CRIADOS */

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return cpf;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}