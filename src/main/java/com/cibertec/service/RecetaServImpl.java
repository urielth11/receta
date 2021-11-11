package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Receta;
import com.cibertec.repository.RecetaRepo;
import com.cibertec.service.espec.RecetaServ;

@Service
public class RecetaServImpl implements RecetaServ {

	@Autowired
	RecetaRepo repo;

	@Override
	public List<Receta> list() {
		return repo.findAll();
	}

	@Override
	public Receta save(Receta obj) {
		return repo.save(obj);
	}

	@Override
	public Receta find(int id) {
		return repo.findById(id).get();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

}
