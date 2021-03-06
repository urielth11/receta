package com.cibertec.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.entity.Receta;
import com.cibertec.entity.Usuario;
import com.cibertec.service.espec.RecetaServ;

@Controller
@RequestMapping(value = "/receta")
public class RecetaCont {
	
	@Autowired
	RecetaServ serv;
	
	@RequestMapping(value = {"/",""})
	public String index(Model model) {
		Map<String, Object> salida = new HashMap<>();
		Receta obj = new Receta();
		
		salida.put("mensaje", "Receta reciente");
		salida.put("obj", obj);
		
		model.addAllAttributes(salida);
		return "receta/nuevo";
	}
	
	@RequestMapping(value = {"/nuevo"})
	public String nuevo(Model model) {
		Map<String, Object> salida = new HashMap<>();
		Receta obj = new Receta();
		
		salida.put("mensaje", "Receta reciente");
		salida.put("obj", obj);
		
		model.addAllAttributes(salida);
		return "receta/nuevo";
	}
	
	@RequestMapping(value = {"/editar/{recetaId}"})
	public String editar(Model model, HttpSession session, @PathVariable Integer recetaId) {
		Map<String, Object> salida = new HashMap<>();
		
		Receta obj = serv.find(recetaId);
		
		salida.put("mensaje", "Receta reciente");
		salida.put("obj", obj);
		
		model.addAllAttributes(salida);
		return "receta/nuevo";
	}
	
	@RequestMapping("/eliminar/{recetaId}")
	public String eliminar(Model model, HttpSession session, @PathVariable Integer recetaId) {
		Usuario usuario = new Usuario();
		
		try {
			Receta obj = serv.find(recetaId);
			obj.setActivo(0);
			
			Receta objSalida = serv.save(obj);
			if (objSalida == null) {
				model.addAttribute("mensaje", "Error en el registro");
			} else {
				model.addAttribute("mensaje", "Registro exitoso");
				return "redirect:/inicio";
			}
		} catch (Exception e) {
			model.addAttribute("mensaje", "Error en el registro");
			e.printStackTrace();
		}
		return "receta/nuevo";
	}
	
	@RequestMapping("/registrar")
	public String registra(Model model, HttpSession session, @ModelAttribute Receta obj) {
		Usuario usuario = new Usuario();
		
		try {
			Integer usuarioId = (Integer) session.getAttribute("usuarioLogueadoId");
			usuario.setId(usuarioId);
			
			
			
			obj.setFechaRegistro(new Date());
			obj.setActivo(1);
			obj.setUsuario(usuario);
			
			if(obj.getVideo()!=null) {
			String[] video =  obj.getVideo().split("/");
			obj.setCodigoVideo(video[video.length - 1]);
			}
			Receta objSalida = serv.save(obj);
			if (objSalida == null) {
				model.addAttribute("mensaje", "Error en el registro");
			} else {
				model.addAttribute("mensaje", "Registro exitoso");
				return "redirect:/inicio";
			}
		} catch (Exception e) {
			model.addAttribute("mensaje", "Error en el registro");
			e.printStackTrace();
		}
		return "receta/nuevo";
	}

}
