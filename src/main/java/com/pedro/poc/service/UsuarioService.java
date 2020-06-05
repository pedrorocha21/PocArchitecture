package com.pedro.poc.service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.poc.api.converter.UsuarioConverter;
import com.pedro.poc.api.dto.UsuarioDTO;
import com.pedro.poc.domain.entity.Usuario;
import com.pedro.poc.domain.repository.UsuarioRepository;



@Service
public class UsuarioService {

		@Autowired
		UsuarioRepository usuarioRepository;
				
		@Autowired
		UsuarioConverter usuarioConverter;
		
	    
	    public List<UsuarioDTO> getAllUsuario() {
	        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
	        List<UsuarioDTO> dtos = usuarios.stream().map(s -> usuarioConverter.convertFrom(s))
					.collect(Collectors.toList());
	        
	        return dtos;
	    }

	    public UsuarioDTO getById(Long id) {
	    	UsuarioDTO dto = null;
	        try {
		       dto = usuarioConverter.convertFrom(usuarioRepository.findById(id).get());
	    	}catch(Exception e) {
	    		//Gerar Log de Erro;
	    	}
	        return dto;
	    }

	    public UsuarioDTO saveOrUpdate(UsuarioDTO usuario) {
	    	try {
	    		Usuario user = usuarioRepository.save(usuarioConverter.convertTo(usuario));
	    		return usuarioConverter.convertFrom(user);
	    	}catch(Exception e) {
	    		//Gerar Log de Erro;
	    		return null;
	    	}	    	
	    	
	    }    


	    public boolean delete(Long id) {
	    	
	    	try {
	    		usuarioRepository.deleteById(id);
	    		return true;
	    	}catch(Exception e) {
	    		//Gerar Log de Erro;
	    		return false;
	    	}
	    }	  
	

}
