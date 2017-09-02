package com.lssdeveloper.testes.negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lssdeveloper.negocio.Cliente;
import com.lssdeveloper.negocio.GerenciadoraClientes;

public class GerenciadoraClientesTest {

	@Test
	public void testPesquisaCliente() {
		//criando clientes
		Cliente cliente001 = new Cliente(1, "Leandro Serra", 41, "leandro.serra@gmail.com", 1, true);
		Cliente cliente002 = new Cliente(2, "Amanda Beatriz", 19, "amanda.beatriz@gmail.com", 1, true);
		Cliente cliente003 = new Cliente(3, "Leana Serra", 13, "leana.serra@gmail.com", 1, true);		
		//inserindo na lista de clientes do banco
		List<Cliente> lstClientesDoBanco = new ArrayList<>();
		lstClientesDoBanco.add(cliente001);
		lstClientesDoBanco.add(cliente002);
		
		GerenciadoraClientes gerClientes = new GerenciadoraClientes(lstClientesDoBanco);
		/*
		 * como eu fiz
		 */
		
		//cliente existente na lista de clientes do banco
		assertNotNull(gerClientes.pesquisaCliente(cliente001.getId()));
		//cliente não existente na lista de clientes do banco		
		assertNull(gerClientes.pesquisaCliente(cliente003.getId()));
		//ou
		Cliente cliente = gerClientes.pesquisaCliente(cliente002.getId());
		assertThat(cliente.getId(), is(2));
		
		/*
		 * como foi feito no curso
		 */
		
		Cliente cliente2 = gerClientes.pesquisaCliente(1);
		assertThat(cliente2.getId(), is(1));
		assertThat(cliente2.getEmail(), is("leandro.serra@gmail.com"));
	}
	@Test
	public void testRemoveClientes(){
		//criando clientes
		Cliente cliente001 = new Cliente(1, "Leandro Serra", 41, "leandro.serra@gmail.com", 1, true);
		Cliente cliente002 = new Cliente(2, "Amanda Beatriz", 19, "amanda.beatriz@gmail.com", 1, true);
		Cliente cliente003 = new Cliente(3, "Leana Serra", 13, "leana.serra@gmail.com", 1, true);
		
		//inserindo na lista de clientes do banco
		List<Cliente> lstClientesDoBanco = new ArrayList<>();
		lstClientesDoBanco.add(cliente001);
		lstClientesDoBanco.add(cliente002);
		
		GerenciadoraClientes gerClientes = new GerenciadoraClientes(lstClientesDoBanco);
		
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

}

