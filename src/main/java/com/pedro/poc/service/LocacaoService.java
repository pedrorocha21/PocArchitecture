package com.pedro.poc.service;



import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.poc.api.converter.LocacaoConverter;
import com.pedro.poc.api.dto.LocacaoDTO;
import com.pedro.poc.domain.entity.Filme;
import com.pedro.poc.domain.entity.Usuario;
import com.pedro.poc.domain.entity.Locacao;
import com.pedro.poc.domain.repository.FilmeRepository;
import com.pedro.poc.domain.repository.LocacaoRepository;
import com.pedro.poc.domain.repository.UsuarioRepository;
import com.pedro.poc.exception.FilmeSemQuantidadeTotalException;
import com.pedro.poc.exception.LocadoraException;



@Service
public class LocacaoService {

		@Autowired
		LocacaoRepository locacaoRepository;
		
		@Autowired
		UsuarioRepository usuarioRepository;
		
		@Autowired
		FilmeRepository filmeRepository;
				
		@Autowired
		LocacaoConverter locacaoConverter;
		
	    
	    public List<LocacaoDTO> getAllLocacaoPorUsuario(Long id) {
	    		    	 
	        List<Locacao> locacaos = (List<Locacao>) locacaoRepository.findAllByUsuarioId(id);
	        List<LocacaoDTO> dtos = locacaos.stream().map(s -> locacaoConverter.convertFrom(s))
					.collect(Collectors.toList());
	        
	        return dtos;
	    }
	    
	    public List<LocacaoDTO> getAllLocacaoPorFilme(Long id) {
	    	 
	        List<Locacao> locacaos = (List<Locacao>) locacaoRepository.findAllByFilmeId(id);
	        List<LocacaoDTO> dtos = locacaos.stream().map(s -> locacaoConverter.convertFrom(s))
					.collect(Collectors.toList());
	        
	        return dtos;
	    }

	    public LocacaoDTO getById(Long id) {
	    	LocacaoDTO dto = null;
	        try {
		       dto = locacaoConverter.convertFrom(locacaoRepository.findById(id).get());
	    	}catch(Exception e) {
	    		//Gerar Log de Erro;
	    	}
	        return dto;
	    }

	    public void saveOrUpdate(LocacaoDTO locacao) {
	    	try {
	    		locacaoRepository.save(locacaoConverter.convertTo(locacao));
	    	}catch(Exception e) {
	    		//Gerar Log de Erro;
	    	}
	    }
	    
	    public LocacaoDTO save(LocacaoDTO locacaoDTO) {	    	
	    	
	    	Usuario usuario = null;	    	
	    	Filme filme = null;
	    	
	    	if(locacaoDTO.getFilme() != null || locacaoDTO.getFilme().getId() != null) 
				filme = filmeRepository.findById(locacaoDTO.getFilme().getId()).get();
	    	
	    	if(locacaoDTO.getUsuario() != null || locacaoDTO.getUsuario().getId() != null) 
				usuario = usuarioRepository.findById(locacaoDTO.getUsuario().getId()).get();
	    	
	    	
		    	try {
		    		Locacao locacao = criaLocacao(usuario, filme); 
		    		if(locacao !=null ) {
		    			locacao = locacaoRepository.save(locacao);
		    			return locacaoConverter.convertFrom(locacao);
		    		}
		    	}catch (FilmeSemQuantidadeTotalException e) {
		    		//Gerar Log de Erro para FilmeSemQuantidadeTotalException;
			    	return null;
				}catch (LocadoraException e) {
		    		//Gerar Log de Erro para LocadoraException;
			    	return null;
				}
	    	
	    	//Gerar Log de Erro;
	    	return null;
	    	
	    }
	    
	    public List<LocacaoDTO> renova(Long idAlocacao) {
	    	Locacao locacao = locacaoRepository.findById(idAlocacao).get();
    		    	
	    	if(locacao !=null ) {
		    	try {
		    		Calendar dia = locacao.getDataFinal();		    		
		    		dia.add(Calendar.DAY_OF_MONTH, +7);
		    		locacao.setDataFinal(dia);
		    		locacao.setRenovado(locacao.getRenovado()+1);
		    		locacao = locacaoRepository.save(locacao);
		    		return getAllLocacaoPorUsuario(locacao.getUsuario().getId());
		    	}catch(Exception e) {
		    		//Gerar Log de Erro;
		    	}
	    	}
	    	//Gerar Log de Erro;
	    	return null;
	    	
	    }
	    
	    public List<LocacaoDTO> recebe(Long idAlocacao) {
	    	Locacao locacao = locacaoRepository.findById(idAlocacao).get();
    		    	
	    	if(locacao !=null ) {
		    	try {
		    		Calendar dia = Calendar.getInstance();
		    		locacao.setDataEntrega(dia);
		    		locacao = locacaoRepository.save(locacao);
		    		return getAllLocacaoPorUsuario(locacao.getUsuario().getId());
		    	}catch(Exception e) {
		    		//Gerar Log de Erro;
		    	}
	    	}
	    	//Gerar Log de Erro;
	    	return null;
	    	
	    }
	    
	    public Locacao criaLocacao(Usuario usuario, Filme filme) throws FilmeSemQuantidadeTotalException, LocadoraException{
    	
	    	
			if(usuario == null) 
				throw new LocadoraException("Usuario vazio");

						
			if(filme == null) 
				throw new LocadoraException("Filme vazio");
			
			
			if(filme.getQuantidadeTotal() == 0) {
				throw new FilmeSemQuantidadeTotalException();
			}
	  
	    		Locacao loc = new Locacao();
	    		loc.setUsuario(usuario);
	    		loc.setFilme(filme);
	    		Calendar dia = Calendar.getInstance();
	    		loc.setDataInicial(dia);
	    		Calendar dias = Calendar.getInstance();
	    		dias.add(Calendar.DAY_OF_MONTH,+7);
	    		loc.setDataFinal(dias);
	    		return loc;	    	
	    }
	    
	    


	    public boolean delete(Long id) {
	    	try {
	    		locacaoRepository.deleteById(id);
	    		return true;
	    	}catch(Exception e) {
	    		//Gerar Log de Erro;
	    		return false;
	    	}
	    }


}
