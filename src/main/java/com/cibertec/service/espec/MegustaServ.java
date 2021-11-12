package com.cibertec.service.espec;

import com.cibertec.entity.Megusta;

public interface MegustaServ extends CrudServ<Megusta> {

	public abstract Megusta getPorUsuarioPorReceta(Integer idUsuario, Integer idReceta);
}
