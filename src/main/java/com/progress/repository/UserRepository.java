package com.progress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progress.model.User;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

}
