package com.pedro.poc.domain.repository;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pedro.poc.domain.entity.Locacao;

@Repository
public interface LocacaoRepository  extends CrudRepository<Locacao, Long> {
	
	List<Locacao> findAllByUsuarioId(Long id);
	
	List<Locacao> findAllByFilmeId(Long id);
	
	List<Locacao> findAllByFilmeIdAndDataEntregaIsNull(Long id);	

	List<Locacao> findAllByUsuarioIdAndDataEntregaIsNull(Long id);

}
