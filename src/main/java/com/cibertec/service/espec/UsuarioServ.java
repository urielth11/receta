package com.cibertec.service.espec;

import com.cibertec.entity.Usuario;

public interface UsuarioServ extends CrudServ<Usuario> {

	public abstract Usuario getByCorreo(String correo); 
}
