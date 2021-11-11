package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Receta;

public interface RecetaRepo extends JpaRepository<Receta, Integer> {

}
