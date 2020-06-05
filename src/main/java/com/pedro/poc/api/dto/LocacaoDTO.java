package com.pedro.poc.api.dto;



import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocacaoDTO implements Serializable{
	


	private static final long serialVersionUID = 8968274712832420599L;

	private Long id;

	private UsuarioDTO usuario;

	private FilmeDTO filme;
	
	private Calendar dataInicial;
	
	private Calendar dataFinal;
	
	private Calendar dataEntrega;
	
	private int renovado;

	
}
