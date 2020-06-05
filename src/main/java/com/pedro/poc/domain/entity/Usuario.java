package com.pedro.poc.domain.entity;



import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "usuario")
public class Usuario  implements Serializable {
	


	private static final long serialVersionUID = -3723246002412288079L;

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String nome;
	
	private String sexo;
	
	private String cpf;
	
	private Calendar dataNascimento;
	
	@Transient
	private int numeroFilmes;

}
