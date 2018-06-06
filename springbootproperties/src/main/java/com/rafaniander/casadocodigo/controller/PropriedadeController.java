package com.rafaniander.casadocodigo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafaniander.casadocodigo.entity.Propriedade;
import com.rafaniander.casadocodigo.repository.PropriedadeRepositoy;

@RestController
@RequestMapping("/api")
public class PropriedadeController {
	
	@Autowired
	private PropriedadeRepositoy repositoy;
	
	@RequestMapping(value="/find/{filtro}",method=RequestMethod.GET)
	public List<Propriedade> findByFiltro(@PathVariable("filtro") String filtro) {
		return repositoy.findByFiltro(filtro);
	}

}
