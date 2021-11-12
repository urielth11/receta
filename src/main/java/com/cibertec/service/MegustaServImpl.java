package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Megusta;
import com.cibertec.repository.MegustaRepo;
import com.cibertec.service.espec.MegustaServ;

@Service
public class MegustaServImpl implements MegustaServ {
	
	@Autowired
	MegustaRepo repo;

	@Override
	public List<Megusta> list() {
		return repo.findAll();
	}

	@Override
	public Megusta save(Megusta obj) {
		return repo.save(obj);
	}

	@Override
	public Megusta find(int id) {
		return repo.findById(id).get();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

	@Override
	public Megusta getPorUsuarioPorReceta(Integer idUsuario, Integer idReceta) {
		return repo.getPorUsuarioPorReceta(idUsuario, idReceta);
	}

}
