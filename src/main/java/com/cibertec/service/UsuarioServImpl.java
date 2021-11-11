package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Usuario;
import com.cibertec.repository.UsuarioRepo;
import com.cibertec.service.espec.UsuarioServ;

@Service
public class UsuarioServImpl implements UsuarioServ {

	@Autowired
	UsuarioRepo repo;

	@Override
	public List<Usuario> list() {
		return repo.findAll();
	}

	@Override
	public Usuario save(Usuario obj) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String claveEncriptada = encoder. encode(obj.getPassword());
		obj.setPassword(claveEncriptada);
		
		//return repositori.save(obj);
		return repo.save(obj);
	}

	@Override
	public Usuario find(int id) {
		return repo.findById(id).get();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

}
