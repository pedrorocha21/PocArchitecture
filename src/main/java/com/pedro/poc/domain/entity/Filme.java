package com.pedro.poc.domain.entity;




import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "filme")
public class Filme  implements Serializable {
	
	
	private static final long serialVersionUID = 7036556715546336790L;

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String nome;
	
	private String genero;
	
	private String diretor;	

	@NonNull
	@Column(name = "quantidadeTotal")
	private int quantidadeTotal;
	

}
