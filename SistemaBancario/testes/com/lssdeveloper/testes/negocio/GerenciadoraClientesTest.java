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
import com.lssdeveloper.negocio.IdadeNaoPermitidaException;

public class GerenciadoraClientesTest {

	private Cliente cliente001;
	private Cliente cliente002;
	private Cliente cliente003;
	private int idC1,idC2,idC3; //Para melhor práticas sugerida pelo professor.
	List<Cliente> lstClientesDoBanco;
	GerenciadoraClientes gerClientes;
	private boolean ativo;
	boolean teste;


	@Before
	public void setUp() {

		/* ===========Montagem do Cenário========= */

		// criando clientes
		cliente001 = new Cliente(1, "Leandro Serra", 41, "leandro.serra@gmail.com", 1, true);
		idC1 = cliente001.getId();
		cliente002 = new Cliente(2, "Amanda Beatriz", 18, "amanda.beatriz@gmail.com", 1, false);
		idC2 = cliente002.getId();
		cliente003 = new Cliente(3, "Astolfo Serra", 65, "astolfo.serra@gmail.com", 1, true);
		idC3 = cliente003.getId();

		// inserindo na lista de clientes do banco
		lstClientesDoBanco = new ArrayList<>();
		lstClientesDoBanco.add(cliente001);
		lstClientesDoBanco.add(cliente002);

		gerClientes = new GerenciadoraClientes(lstClientesDoBanco);
		// System.out.println("Before foi executado!");
		ativo = gerClientes.clienteAtivo(idC1);

	}

	@Test
	public void testPesquisaCliente() {
		// cliente existente na lista de clientes do banco
		/* como eu fiz */

		assertNotNull(gerClientes.pesquisaCliente(idC1));

		/* como foi feito no curso */

		/* ========= Verificação =========== */
		Cliente cliente = gerClientes.pesquisaCliente(idC1);
		assertThat(cliente.getId(), is(idC1));
		assertThat(cliente.getEmail(), is("leandro.serra@gmail.com"));
	}

	@Test
	public void testPesquisaCliente_naoExistente() {
		// cliente não existente na lista de clientes do banco
		assertNull(gerClientes.pesquisaCliente(idC3));
	}

	@Test
	public void testRemoveClientes() {

		assertTrue(gerClientes.removeCliente(idC1));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		/*
		 * como foi feito no curso
		 */
		boolean clienteRemovido = gerClientes.removeCliente(idC2);
		assertNull(gerClientes.pesquisaCliente(idC2));
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(0));

	}

	@Test
	public void testRemoveClientes_inexistenteNaLista() {
		assertFalse(gerClientes.removeCliente(idC3));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
	}
	/*
	 * Informa se um determinado cliente está ativo ou não
	 */
	@Test
	public void testCliente_Ativo(){

		assertTrue(ativo);
	}
	@Test
	public void testClienteAtivo_inAtivo(){
		ativo = gerClientes.clienteAtivo(idC2);
		assertFalse(ativo);
	}
	/**
	 * Valida se a idade do cliente está dentro do intervalo permitido (18 - 65).
	 * Teste de Limites
	 * @throws IdadeNaoPermitidaException 
	 */
	@Test
	public void testeValidaIdade_entre18E65() throws IdadeNaoPermitidaException{

		int idade41 = cliente001.getIdade();
		teste = gerClientes.validaIdade(idade41);
		assertTrue(teste);
	}
	@Test
	public void testeValidaIdade_igual18() throws IdadeNaoPermitidaException{

		int idade18 = cliente002.getIdade();
		teste = gerClientes.validaIdade(idade18);
		assertTrue(teste);
	}
	@Test
	public void testeValidaIdade_igual65() throws IdadeNaoPermitidaException{

		int idade65 = cliente003.getIdade();
		teste = gerClientes.validaIdade(idade65);
		assertTrue(teste);
	}	
	@Test
	public void testeValidaIdade_menorQ18(){
		int idade17 = 17;
		try {
			teste = gerClientes.validaIdade(idade17);
			fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
		
	}
	@Test
	public void testeValidaIdade_maiorQ65(){
		int idade66 = 66;
		try {
			teste = gerClientes.validaIdade(idade66);
			fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
		
	}	
	@After
	public void tearDown() {
		gerClientes.limpa();
		// System.out.println("After foi executado!");
	}

}
