package com.pedro.poc.api.converter;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pedro.poc.api.dto.UsuarioDTO;
import com.pedro.poc.domain.entity.Usuario;
import com.pedro.poc.domain.entity.Locacao;
import com.pedro.poc.domain.repository.LocacaoRepository;
import com.pedro.poc.domain.repository.UsuarioRepository;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class UsuarioConverter {
	
	MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
	MapperFacade mapper;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	LocacaoRepository locacaoRepository;
	
	public UsuarioConverter() {
		
		mapperFactory.classMap(Usuario.class, UsuarioDTO.class).byDefault().register();
		
		mapper = mapperFactory.getMapperFacade();
		
	}
	
	public UsuarioDTO convertFrom(Usuario usuario) {
		numeroFilmes(usuario);
		return mapper.map(usuario, UsuarioDTO.class);
	}
	
	public void numeroFilmes(Usuario usuario) {		
		if(usuario!=null && usuario.getId()!=null) {
			Usuario user = usuarioRepository.findById(usuario.getId()).get();
			if(user!=null) {
				List<Locacao> locacao = locacaoRepository.findAllByUsuarioIdAndDataEntregaIsNull(user.getId());
				if(locacao!=null) {
					usuario.setNumeroFilmes(locacao.size());
				}else {
					usuario.setNumeroFilmes(0);
				}
			}
		}
	}
	
	
	public Usuario convertTo(UsuarioDTO usuarioDTO) {
		return mapper.map(usuarioDTO, Usuario.class);
	}
	
	public ArrayList<Usuario> convertAllTo(ArrayList<UsuarioDTO> usuarioDTO) {
		return (ArrayList<Usuario>) mapper.mapAsList(usuarioDTO, Usuario.class);
	}
	
	public ArrayList<UsuarioDTO> convertAllFrom(ArrayList<Usuario> usuario) {
		usuario.stream().forEach(user -> numeroFilmes(user));
		return (ArrayList<UsuarioDTO>)mapper.mapAsList(usuario, UsuarioDTO.class);
	}

}
