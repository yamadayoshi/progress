package com.progress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.progress.model.Client;

public interface ClientRepository extends CrudRepository<Client, Integer>{
	Iterable<Client> findByClientId(int id);
	
	Iterable<Client> findByClientName(String name);

	@Query("SELECT count(*) as qtd FROM Client")
	List<Long> totalCount();	
}
