package com.progress.repository;

import org.springframework.data.repository.CrudRepository;

import com.progress.model.Screen;

public interface ScreenRepository extends CrudRepository<Screen, Integer>{
	
	public Iterable<Screen> findByScreenId(int id); 

}
