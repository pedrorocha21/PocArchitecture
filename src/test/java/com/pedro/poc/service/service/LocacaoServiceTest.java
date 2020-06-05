package com.pedro.poc.service.service;



import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import com.pedro.poc.domain.entity.Filme;
import com.pedro.poc.domain.entity.Usuario;
import com.pedro.poc.exception.FilmeSemQuantidadeTotalException;
import com.pedro.poc.exception.LocadoraException;
import com.pedro.poc.service.LocacaoService;


public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	@Test(expected = FilmeSemQuantidadeTotalException.class)
	public void testLocacao_filmeSemQuantidadeTotal() throws Exception{
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 2", 0);
		//acao
		service.criaLocacao(usuario, filme);
	}


	
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemQuantidadeTotalException{
		//cenario
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme 2", 1);
		
		//acao
		try {
			service.criaLocacao(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	

	@Test
	public void testLocacao_FilmeVazio() throws FilmeSemQuantidadeTotalException, LocadoraException{
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.criaLocacao(usuario, null);
	}
}
