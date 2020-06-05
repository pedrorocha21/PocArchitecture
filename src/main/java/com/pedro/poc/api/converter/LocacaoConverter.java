package com.pedro.poc.api.converter;



import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.pedro.poc.api.dto.LocacaoDTO;
import com.pedro.poc.domain.entity.Locacao;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class LocacaoConverter {
	
	MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
	MapperFacade mapper;
	public LocacaoConverter() {
		
		mapperFactory.classMap(Locacao.class, LocacaoDTO.class).byDefault().register();
		
		mapper = mapperFactory.getMapperFacade();
		
	}
	
	public LocacaoDTO convertFrom(Locacao locacao) {
		return mapper.map(locacao, LocacaoDTO.class);
	}
	
	public Locacao convertTo(LocacaoDTO locacaoDTO) {
		return mapper.map(locacaoDTO, Locacao.class);
	}
	
	public ArrayList<Locacao> convertAllTo(ArrayList<LocacaoDTO> locacaoDTO) {
		return (ArrayList<Locacao>) mapper.mapAsList(locacaoDTO, Locacao.class);
	}
	
	public ArrayList<LocacaoDTO> convertAllFrom(ArrayList<Locacao> locacao) {
		return (ArrayList<LocacaoDTO>)mapper.mapAsList(locacao, LocacaoDTO.class);
	}

}
