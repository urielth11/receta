package com.cibertec.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Rol;
import com.cibertec.entity.Usuario;
import com.cibertec.repository.RolRepo;
import com.cibertec.repository.UsuarioRepo;

@Service
public class LoginServImpl implements UserDetailsService {
	
	@Autowired
	UsuarioRepo repo;
	
	@Autowired
	RolRepo rolRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDet=null;
		
		Usuario bean;
		//bean=usuarioDAO.iniciarSesion(username);	
		try {
			//bean=repo.getByLogin(username);	
			bean=repo.getByCorreo(username);	
			Rol rolUsuario = rolRepo.getPorUsuario(bean.getId());
			
			System.out.println(bean.getLogin() + " - " +bean.getPassword());
			Set<GrantedAuthority> rol=new HashSet<GrantedAuthority>();
			rol.add(new SimpleGrantedAuthority(rolUsuario.getNombre()));
			//rol.add(new SimpleGrantedAuthority(bean.getRol().getCargo()));
			
			userDet=new User(bean.getCorreo(), bean.getPassword(),rol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return userDet;
	}

}
