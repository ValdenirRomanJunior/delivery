package br.com.dynamous.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dynamous.delivery.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	

}
