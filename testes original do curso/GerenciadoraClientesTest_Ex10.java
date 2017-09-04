package com.lssdeveloper.teste.negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lssdeveloper.negocio.Cliente;
import com.lssdeveloper.negocio.GerenciadoraClientes;
import com.lssdeveloper.negocio.IdadeNaoPermitidaException;

public class GerenciadoraClientesTest_Ex10 {

	private GerenciadoraClientes gerClientes;
	private int idCLiente01 = 1;
	private	int idCLiente02 = 2;
	
	@Before
	public void setUp() {
	
		/* ========== Montagem do cenario ========== */
		
		// criando alguns clientes
		Cliente cliente01 = new Cliente(idCLiente01, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCLiente02, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 1, true);
		
		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();
	}
	
	/**
	 * Teste b�sico da pesquisa de um cliente a partir do seu ID.
	 * 
	 * @author Gustavo Farias
	 * @date 21/01/2035
	 */
	@Test
	public void testPesquisaCliente() {

		/* ========== Execu��o ========== */
		Cliente cliente = gerClientes.pesquisaCliente(idCLiente01);
		
		/* ========== Verifica��es ========== */
		assertThat(cliente.getId(), is(idCLiente01));
		
	}
	
	/**
	 * Teste b�sico da pesquisa por um cliente que n�o existe.
	 * 
	 * @author Gustavo Farias
	 * @date 21/01/2035
	 */
	@Test
	public void testPesquisaClienteInexistente() {

		/* ========== Execu��o ========== */
		Cliente cliente = gerClientes.pesquisaCliente(1001);
		
		/* ========== Verifica��es ========== */
		assertNull(cliente);
		
	}
	
	/**
	 * Teste b�sico da remo��o de um cliente a partir do seu ID.
	 * 
	 * @author Gustavo Farias
	 * @date 21/01/2035
	 */
	@Test
	public void testRemoveCliente() {
		
		/* ========== Execu��o ========== */
		boolean clienteRemovido = gerClientes.removeCliente(idCLiente02);
		
		/* ========== Verifica��es ========== */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCLiente02));
		
	}
	
	/**
	 * Teste da tentativa de remo��o de um cliente inexistente.
	 * 
	 * @author Gustavo Farias
	 * @date 21/01/2035
	 */
	@Test
	public void testRemoveClienteInexistente() {

	
		/* ========== Execu��o ========== */
		boolean clienteRemovido = gerClientes.removeCliente(1001);
		
		/* ========== Verifica��es ========== */
		assertThat(clienteRemovido, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
		
	}
	
	/**
	 * Valida��o da idade de um cliente quando a mesma est� no intervalo permitido.
	 * 
	 * @author Gustavo Farias
	 * @throws IdadeNaoPermitidaException 
	 * @date 21/01/2035
	 */
	@Test
	public void testClienteIdadeAceitavel() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cen�rio ========== */		
		Cliente cliente = new Cliente(1, "Gustavo", 25, "guga@gmail.com", 1, true);
		
		/* ========== Execu��o ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		/* ========== Verifica��es ========== */
		assertTrue(idadeValida);	
	}
	
	/**
	 * Valida��o da idade de um cliente quando a mesma est� no intervalo permitido.
	 * 
	 * @author Gustavo Farias
	 * @throws IdadeNaoPermitidaException 
	 * @date 21/01/2035
	 */
	@Test
	public void testClienteIdadeAceitavel_02() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cen�rio ========== */		
		Cliente cliente = new Cliente(1, "Gustavo", 18, "guga@gmail.com", 1, true);
		
		/* ========== Execu��o ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		/* ========== Verifica��es ========== */
		assertTrue(idadeValida);	
	}
	
	/**
	 * Valida��o da idade de um cliente quando a mesma est� no intervalo permitido.
	 * 
	 * @author Gustavo Farias
	 * @throws IdadeNaoPermitidaException 
	 * @date 21/01/2035
	 */
	@Test
	public void testClienteIdadeAceitavel_03() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cen�rio ========== */		
		Cliente cliente = new Cliente(1, "Gustavo", 65, "guga@gmail.com", 1, true);
		
		/* ========== Execu��o ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		/* ========== Verifica��es ========== */
		assertTrue(idadeValida);	
	}
	
	/**
	 * Valida��o da idade de um cliente quando a mesma est� abaixo intervalo permitido.
	 * 
	 * @author Gustavo Farias
	 * @throws IdadeNaoPermitidaException 
	 * @date 21/01/2035
	 */
	@Test
	public void testClienteIdadeAceitavel_04() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cen�rio ========== */		
		Cliente cliente = new Cliente(1, "Gustavo", 17, "guga@gmail.com", 1, true);

		/* ========== Execu��o ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verifica��es ========== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}	
	}
	
	/**
	 * Valida��o da idade de um cliente quando a mesma est� acima intervalo permitido.
	 * 
	 * @author Gustavo Farias
	 * @throws IdadeNaoPermitidaException 
	 * @date 21/01/2035
	 */
	@Test
	public void testClienteIdadeAceitavel_05() throws IdadeNaoPermitidaException {
		
		/* ========== Montagem do Cen�rio ========== */		
		Cliente cliente = new Cliente(1, "Gustavo", 66, "guga@gmail.com", 1, true);
		/* ========== Execu��o ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verifica��es ========== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}	
	}
	
}

// Valores Limites