package com.pedro.poc.domain.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pedro.poc.domain.entity.Filme;


@Repository
public interface FilmeRepository  extends CrudRepository<Filme, Long> {
	
	

}
