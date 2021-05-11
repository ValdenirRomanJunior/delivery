package br.com.dynamous.delivery.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dynamous.delivery.domain.Categoria;
import br.com.dynamous.delivery.repositories.CategoriaRepository;
import br.com.dynamous.delivery.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {	
		Optional<Categoria> obj =repo.findById(id);		
			return obj.orElseThrow(()-> new ObjectNotFoundException
					("objeto nao encontrado Id:" +id + ",Tipo:" + Categoria.class.getName()));	
		
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

}
