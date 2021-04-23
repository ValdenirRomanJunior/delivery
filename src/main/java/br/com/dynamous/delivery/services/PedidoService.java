package br.com.dynamous.delivery.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dynamous.delivery.domain.Pedido;
import br.com.dynamous.delivery.repositories.PedidoRepository;
import br.com.dynamous.delivery.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj= repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException
				("objeto nao encontrado"+ id+ ",Tipo"+Pedido.class.getName()));
	}

}
