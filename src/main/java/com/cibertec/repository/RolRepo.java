package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.entity.Rol;

public interface RolRepo extends JpaRepository<Rol, Integer> {
	//@Query("Select a from Rol a where nombre like :p_id")
	@Query(value = "SELECT * FROM rol r INNER JOIN usuario u on r.id = u.rol_id WHERE u.id = ?1", nativeQuery = true)
	public abstract Rol getPorUsuario(Integer id);
}
