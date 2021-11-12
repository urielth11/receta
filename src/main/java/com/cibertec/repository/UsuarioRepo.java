package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

	public abstract Usuario getByLogin(String login); 
	public abstract Usuario getByCorreo(String correo); 
}
