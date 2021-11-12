package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.entity.Receta;

public interface RecetaRepo extends JpaRepository<Receta, Integer> {
	@Query(value = "SELECT * FROM receta r INNER JOIN megusta m on r.id = m.receta_id WHERE m.usuario_id = ?1 and m.activo = 1", nativeQuery = true)
	public abstract List<Receta> getMegustaPorUsuario(Integer id);
	
	@Query(value = "SELECT * FROM receta r WHERE r.usuario_id = ?1 and r.activo = 1", nativeQuery = true)
	public abstract List<Receta> getPorUsuario(Integer id); 
}
