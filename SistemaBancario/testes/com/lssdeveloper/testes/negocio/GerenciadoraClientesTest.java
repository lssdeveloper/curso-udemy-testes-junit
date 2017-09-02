package com.lssdeveloper.testes.negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lssdeveloper.negocio.Cliente;
import com.lssdeveloper.negocio.GerenciadoraClientes;

public class GerenciadoraClientesTest {

	private Cliente cliente001;
	private Cliente cliente002;
	private Cliente cliente003;
	List<Cliente> lstClientesDoBanco;
	GerenciadoraClientes gerClientes;

	@Before
	public void setUp() {

		/* ===========Montagem do Cenário========= */

		// criando clientes
		cliente001 = new Cliente(1, "Leandro Serra", 41, "leandro.serra@gmail.com", 1, true);
		cliente002 = new Cliente(2, "Amanda Beatriz", 19, "amanda.beatriz@gmail.com", 1, true);
		cliente003 = new Cliente(3, "Leana Serra", 13, "leana.serra@gmail.com", 1, true);
		
		// inserindo na lista de clientes do banco
		lstClientesDoBanco = new ArrayList<>();
		lstClientesDoBanco.add(cliente001);
		lstClientesDoBanco.add(cliente002);
		
		gerClientes = new GerenciadoraClientes(lstClientesDoBanco);
		//System.out.println("Before foi executado!");

	}

	@Test
	public void testPesquisaCliente() {

		/*
		 * como eu fiz
		 */
		// cliente existente na lista de clientes do banco
		assertNotNull(gerClientes.pesquisaCliente(cliente001.getId()));
		// cliente não existente na lista de clientes do banco
		assertNull(gerClientes.pesquisaCliente(cliente003.getId()));


		/*
		 * como foi feito no curso
		 */
		/* ========= Execução ======== */

		/* ========= Verificação =========== */
		Cliente cliente = gerClientes.pesquisaCliente(cliente001.getId());
		assertThat(cliente.getId(), is(1));
		assertThat(cliente.getEmail(), is("leandro.serra@gmail.com"));
	}

	@Test
	public void testRemoveClientes() {

		// inserindo na lista de clientes do banco
		List<Cliente> lstClientesDoBanco = new ArrayList<>();
		lstClientesDoBanco.add(cliente001);
		lstClientesDoBanco.add(cliente002);

		assertTrue(gerClientes.removeCliente(cliente001.getId()));
		assertFalse(gerClientes.removeCliente(cliente003.getId()));

		/*
		 * como foi feito no curso
		 */
		boolean clienteRemovido = gerClientes.removeCliente(cliente002.getId());
		assertNull(gerClientes.pesquisaCliente(cliente002.getId()));
		assertThat(gerClientes.getClientesDoBanco().size(), is(0));
		assertThat(clienteRemovido, is(true));

	}
	@After
	public void tearDown(){
		gerClientes.limpa();
		//System.out.println("After foi executado!");
	}

}
