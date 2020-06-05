package com.pedro.poc.api.dto;



import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO implements Serializable{
	

	private static final long serialVersionUID = -1086041401716350724L;


	private Long id;

	private String nome;
	
	private String sexo;
	
	private String cpf;
	
	private Calendar dataNascimento;
	
	private int numeroFilmes;
	


}
