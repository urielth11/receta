package com.cibertec.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.entity.Receta;
import com.cibertec.service.espec.RecetaServ;
import com.cibertec.util.Constantes;

@RestController
@RequestMapping("rest/receta")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiRecetaCont {

	@Autowired
	private RecetaServ service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Receta>> listar(){
		List<Receta> lista=service.list();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registrar(@RequestBody Receta obj){
		
		Map<String, Object> salida=new HashMap<>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setActivo(1);
			Receta objSalida=service.save(obj);
			if(objSalida==null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		}catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje",Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
		
	}
}
