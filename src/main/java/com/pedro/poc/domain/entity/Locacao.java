package com.pedro.poc.domain.entity;




import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "locacao")
public class Locacao  implements Serializable {
	

	private static final long serialVersionUID = -2502317721776679706L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Filme filme;
	
	private Calendar dataInicial;
	
	private Calendar dataFinal;
	
	private Calendar dataEntrega;
	

	@Column(name = "renovado", nullable = false, columnDefinition = "int default 0") 
	private int renovado;

}
