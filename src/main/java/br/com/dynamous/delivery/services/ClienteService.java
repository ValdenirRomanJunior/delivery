package br.com.dynamous.delivery.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dynamous.delivery.domain.Cliente;
import br.com.dynamous.delivery.repositories.ClienteRepository;
import br.com.dynamous.delivery.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj= repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException
				("objeto nao encontrado"+ id+ ",Tipo"+Cliente.class.getName()));
	}

}
