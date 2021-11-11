package com.cibertec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.entity.Receta;
import com.cibertec.repository.RecetaRepo;
import com.cibertec.service.espec.RecetaServ;

@Controller
public class LoginCont {
	
	@Autowired
	RecetaServ recetaSer;

	@RequestMapping(value = {"/",""})
	public String principal(Model model, HttpSession  session) {
		
		
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
			User user = (User)authentication.getPrincipal();
			session.setAttribute("usuarioLogueado", user.getUsername());
			session.setAttribute("rolLogueado", roles.stream().findFirst().get());
			
			System.out.println(user.getUsername());
			System.out.println("");
			System.out.println(roles.stream().findFirst().get());
			System.out.println("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/inicio";
	}
	
	@RequestMapping(value = {"/login"})
	public String login(Model model) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}
	
	@RequestMapping(value = {"/inicio","/inicio/"})
	public String inicio(Model model) {
		Map<String, Object> salida = new HashMap<>();
		
		try {
			List<Receta> recetas = recetaSer.list();
			salida.put("recetas", recetas);
			salida.put("titulo", "Últimas recetas");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAllAttributes(salida);
		return "inicio";
	}
}
