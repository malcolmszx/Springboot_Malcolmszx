package com.kingdee.abc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingdee.abc.model.Man;

public interface ManRepository extends JpaRepository<Man, Integer> {
	
	public Optional<Man> findById(Integer id);
	
}
