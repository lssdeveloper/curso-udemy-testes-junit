package com.lssdeveloper.teste.negocio;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lssdeveloper.negocio.Cliente;
import com.lssdeveloper.negocio.GerenciadoraClientes;

public class GerenciadoraClientesTest_Ex1 {

	@Test
	public void testPesquisaCliente() {

		// criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Leandro Serra", 31, "lssdeveloper@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Amanda Beatriz", 34, "abdeveloper@gmail.com", 2, true);
		
		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientesDoBanco);
		
		Cliente cliente = gerClientes.pesquisaCliente(1);
		
		assertThat(cliente.getId(), is(1));
		assertThat(cliente.getEmail(), is("lssdeveloper@gmail.com"));
		
	}

}
