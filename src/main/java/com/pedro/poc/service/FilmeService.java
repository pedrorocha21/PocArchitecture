package com.pedro.poc.service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.poc.api.converter.FilmeConverter;
import com.pedro.poc.api.dto.FilmeDTO;
import com.pedro.poc.domain.entity.Filme;
import com.pedro.poc.domain.entity.Locacao;
import com.pedro.poc.domain.repository.FilmeRepository;
import com.pedro.poc.domain.repository.LocacaoRepository;



@Service
public class FilmeService {

		@Autowired
		FilmeRepository filmeRepository;
		
		@Autowired
		LocacaoRepository locacaoRepository;
				
		@Autowired
		FilmeConverter filmeConverter;
		
	    
	    public List<FilmeDTO> getAllFilme() {
	        List<Filme> filmes = (List<Filme>) filmeRepository.findAll();
	        List<FilmeDTO> dtos = filmes.stream().map(s -> filmeConverter.convertFrom(s))
					.collect(Collectors.toList());
	        dtos.stream().forEach(filmeDTO -> {
	        	filmeDTO.setQuantidadeDisponivel(getQuantidadeDisponivel(filmeDTO.getId()));
	        });
	        return dtos;
	    }
	    
	    public int getQuantidadeDisponivel(Long id) {
	    	Filme filme = filmeRepository.findById(id).get();
	    	if(filme!=null) {
	    		List<Locacao> locacaos = locacaoRepository.findAllByFilmeIdAndDataEntregaIsNull(id);
	    		if(locacaos!=null)
	    			return (filme.getQuantidadeTotal() - locacaos.size());
	    	}
	    	return 0;
	    }


	    public FilmeDTO getById(Long id) {
	    	FilmeDTO dto = null;
	        try {
		       dto = filmeConverter.convertFrom(filmeRepository.findById(id).get());
	    	}catch(Exception e) {
	    		//Gerar Log de Erro;
	    		dto = null;
	    	}
	        return dto;
	    }

	    public FilmeDTO saveOrUpdate(FilmeDTO filmeDTO) {
	    	try {
	    		Filme filme = filmeRepository.save(filmeConverter.convertTo(filmeDTO));
	    		return filmeConverter.convertFrom(filme);
	    	}catch(Exception e) {
	    		//Gerar Log de Erro;
	    		return null;
	    	}
	    }
	    


	    public boolean delete(Long id) {
	    	try {
	    		filmeRepository.deleteById(id);
	    		return true;
	    	}catch(Exception e) {
	    		//Gerar Log de Erro;
	    		return false;
	    	}
	    }

	

}
