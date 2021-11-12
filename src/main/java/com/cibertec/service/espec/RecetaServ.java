package com.cibertec.service.espec;

import java.util.List;

import com.cibertec.entity.Receta;

public interface RecetaServ extends CrudServ<Receta> {

	public abstract List<Receta> getMegustaPorUsuario(Integer id);
	
	public abstract List<Receta> getPorUsuario(Integer id); 
	
}
