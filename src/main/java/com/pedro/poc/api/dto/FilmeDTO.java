package com.pedro.poc.api.dto;



import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmeDTO implements Serializable{
	


	private static final long serialVersionUID = 5659021073912342344L;

	private Long id;

	private String nome;
	
	private String genero;
	
	private String diretor;

	private int quantidadeTotal;
	
	private int quantidadeDisponivel;


}
