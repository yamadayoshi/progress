package com.progress.repository;

import org.springframework.data.repository.CrudRepository;

import com.progress.classes.Request;

public interface RequestRepository extends CrudRepository<Request, Integer> {

}
