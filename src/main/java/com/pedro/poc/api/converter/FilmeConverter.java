package com.pedro.poc.api.converter;



import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.pedro.poc.api.dto.FilmeDTO;
import com.pedro.poc.domain.entity.Filme;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class FilmeConverter {
	
	MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
	MapperFacade mapper;
	public FilmeConverter() {
		
		mapperFactory.classMap(Filme.class, FilmeDTO.class).byDefault().register();
		
		mapper = mapperFactory.getMapperFacade();
		
	}
	
	public FilmeDTO convertFrom(Filme filme) {
		return mapper.map(filme, FilmeDTO.class);
	}
	
	public Filme convertTo(FilmeDTO filmeDTO) {
		return mapper.map(filmeDTO, Filme.class);
	}
	
	public ArrayList<Filme> convertAllTo(ArrayList<FilmeDTO> filmeDTO) {
		return (ArrayList<Filme>) mapper.mapAsList(filmeDTO, Filme.class);
	}
	
	public ArrayList<FilmeDTO> convertAllFrom(ArrayList<Filme> filme) {
		return (ArrayList<FilmeDTO>)mapper.mapAsList(filme, FilmeDTO.class);
	}

}
