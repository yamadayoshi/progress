package com.progress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.progress.model.Request;

public interface RequestRepository extends CrudRepository<Request, Integer> {
	
	Iterable<Request> findByRequestId(int id);
	
	List<Request> findByStatus(String status);
	
	@Query("SELECT count(*) as qtd FROM Request r WHERE r.status=:status")
	List<Long> countByStatus(@Param("status") String status);
	
	@Query("SELECT count(*) as qtd FROM Request")
	List<Long> totalCount();
	
	@Query("SELECT count(*) as qtd FROM Request r WHERE month(r.requestEntryDate)=:month and year(r.requestEntryDate)=:year")
	List<Integer> countByMonth(@Param("month") int month, @Param("year") int year);
}
