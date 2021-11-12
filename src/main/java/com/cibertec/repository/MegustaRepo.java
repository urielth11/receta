package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Megusta;

public interface MegustaRepo extends JpaRepository<Megusta, Integer> {

	@Query(value = "SELECT * FROM megusta m WHERE m.usuario_id = ?1 and m.receta_id = ?2", nativeQuery = true)
	public abstract Megusta getPorUsuarioPorReceta(Integer idUsuario, Integer idReceta);
}
