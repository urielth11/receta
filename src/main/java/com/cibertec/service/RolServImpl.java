package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Rol;
import com.cibertec.repository.RolRepo;
import com.cibertec.service.espec.RolServ;

@Service
public class RolServImpl implements RolServ {

	@Autowired
	RolRepo repo;

	@Override
	public List<Rol> list() {
		return repo.findAll();
	}

	@Override
	public Rol save(Rol obj) {
		return repo.save(obj);
	}

	@Override
	public Rol find(int id) {
		return repo.findById(id).get();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

}
