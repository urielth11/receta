package com.cibertec.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.entity.Rol;
import com.cibertec.entity.Usuario;
import com.cibertec.service.espec.UsuarioServ;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioCont {

	@Autowired
	UsuarioServ usuarioServicio;
	
	@RequestMapping(value = {"/",""})
	public String index(Model model) {
		Map<String, Object> salida = new HashMap<>();
		
		salida.put("mensaje", "Registrar Usuario");
		
		model.addAllAttributes(salida);
		return "registro";
	}
	
	@RequestMapping("/registrar")
	public String registra(Model model, @ModelAttribute Usuario obj) {
		Map<String, Object> salida = new HashMap<>();
		Rol rol = new Rol();
		rol.setId(1);
		
		try {
			obj.setRol(rol);
			obj.setFechaRegistro(new Date());
			obj.setActivo(1);
			Usuario objSalida = usuarioServicio.save(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en el registro");
			} else {
				salida.put("mensaje", "Registro exitoso");
				return "redirect:/";
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error en el registro");
			e.printStackTrace();
		}
		
		model.addAllAttributes(salida);
		return "registro";
	}
}
