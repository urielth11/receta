package com.cibertec.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.entity.Megusta;
import com.cibertec.entity.Receta;
import com.cibertec.entity.Usuario;
import com.cibertec.service.espec.MegustaServ;
import com.cibertec.service.espec.UsuarioServ;

@Controller
@RequestMapping(value = "/megusta")
public class MegustaCont {

	@Autowired
	MegustaServ serv;
	
	@RequestMapping(value = {"/",""})
	public String index(Model model) {
		Map<String, Object> salida = new HashMap<>();
		
		salida.put("mensaje", "Receta que me gustan");
		
		model.addAllAttributes(salida);
		return "registro";
	}
	
	@RequestMapping(value = {"/registrar/{recetaId}"})
    public String index2(Model model, HttpSession session, @PathVariable Integer recetaId) {
        Map<String, Object> salida = new HashMap<>();
        Megusta obj = new Megusta();
        Usuario usu = new Usuario();
        Receta rec = new Receta();

        try {
        	Integer usuarioId = (Integer) session.getAttribute("usuarioLogueadoId");
        	obj=serv.getPorUsuarioPorReceta(usuarioId,recetaId);
        	
        	if(obj == null) {
        		obj = new Megusta();
        		usu.setId(usuarioId);
            	rec.setId(recetaId);
            	
            	obj.setActivo(1);
            	obj.setFechaRegistro(new Date());
            	obj.setUsuario(usu);
            	obj.setReceta(rec);
        	}else {
        		if(obj.getActivo() == 1) {
        			obj.setActivo(0);
        		}else if(obj.getActivo() == 0){
        			obj.setActivo(1);
        		}
        	}
        	
        	serv.save(obj);
        	salida.put("mensaje", "Receta agregado a Megusta");
        } catch (Exception e) {
            salida.put("mensaje", "Error al dar o quitar megusta");
            e.printStackTrace();
        }

        model.addAllAttributes(salida);
        return "redirect:/inicio";
    }
}
