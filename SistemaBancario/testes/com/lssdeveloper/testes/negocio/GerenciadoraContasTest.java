package com.lssdeveloper.testes.negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lssdeveloper.negocio.ContaCorrente;
import com.lssdeveloper.negocio.GerenciadoraContas;

public class GerenciadoraContasTest {

	private GerenciadoraContas gerContas;
	private ContaCorrente cc1, cc2, cc3;
	private List<ContaCorrente> lstContaDoBanco;
	private int idConta1;
	private int idConta2;
	private int idConta3;
	private double saldoC1;
	private double saldoC2;	
	private double saldoC3;	

	@Before
	public void setUp() {
		/* =========Montagem do cenário========= */
		// criando contas
		cc1 = new ContaCorrente(1, 200.0, true);
		cc2 = new ContaCorrente(2, 0, true);
		cc3 = new ContaCorrente(3, -100.0, true);
		
		idConta1 = cc1.getId();
		idConta2 = cc2.getId();
		idConta3 = cc3.getId();
		
		// inserindo as contas na lista do banco
		lstContaDoBanco = new ArrayList<>();
		lstContaDoBanco.add(cc1);
		lstContaDoBanco.add(cc2);
		lstContaDoBanco.add(cc3);		
		gerContas = new GerenciadoraContas(lstContaDoBanco);
	}

	@After
	public void tearDown() {
		gerContas.limpar();
	}

	@Test
	public void testTransfereValor() {
		/* =========Execução========== */
		boolean sucesso = gerContas.transfereValor(idConta1, 100.0, idConta2);
		saldoC1 = cc1.getSaldo();
		saldoC2 = cc2.getSaldo();
		/* =========Verificação======== */
		assertTrue(sucesso);
		//ou
		assertThat(sucesso, is(true));
		assertThat(saldoC2, is(100.0));
		assertThat(saldoC1, is(100.0));

	}

	/*
	 * Transferência de um valor da conta de um cliente para outro 
	 * Teste quando não há saldo suficiente, mas saldo igual a zero.
	 */
	@Test
	public void testTransfereValor_SaldoInsuficiente_igualAZero() {
		boolean semSucesso = gerContas.transfereValor(idConta2, 10.0, idConta1);
		saldoC1 = cc1.getSaldo();
		saldoC2 = cc2.getSaldo();
		assertFalse(semSucesso);
		assertThat(saldoC1, is(200.0));
		assertThat(saldoC2, is(0.0));
	}
	/*
	 * Transferência de um valor da conta de um cliente para outro 
	 * Teste quando não há saldo suficiente, mas saldo positivo.
	 */
	@Test
	public void testTransfereValor_SaldoInsuficiente_masPositivo() {
		boolean sucesso = gerContas.transfereValor(idConta1, 100.0, idConta2);
		boolean semSucesso = gerContas.transfereValor(idConta2, 300.0, idConta1);
		saldoC1 = cc1.getSaldo();
		saldoC2 = cc2.getSaldo();
		assertTrue(sucesso);
		assertFalse(semSucesso);
		//Teste Limite de Valores
		assertThat(saldoC1, is(100.0));
		assertThat(saldoC2, is(100.0));
	}
	/*
	 * Transferência de um valor da conta de um cliente para outro 
	 * Teste quando não há saldo suficiente, pois o saldo é negativo
	 */
	@Test
	public void testTransfereValor_SaldoInsuficiente_Negativo() {
		boolean sucesso = gerContas.transfereValor(idConta1,  200.0, idConta2);
		boolean semSucesso = gerContas.transfereValor(idConta1, 100.0, idConta2);
		saldoC1 = cc1.getSaldo();
		saldoC2 = cc2.getSaldo();
		assertTrue(sucesso);
		assertFalse(semSucesso);
		//ou
		assertThat(saldoC1, is(0.0));
		assertThat(saldoC2, is(200.0));
	}
	/*
	 * Transferência de um valor da conta de um cliente para outro 
	 * Teste quando não há saldo suficiente, pois o saldo é negativo
	 */
	@Test
	public void testTransfereValor_SaldoInsuficiente_saldoNegativo() {
		boolean semSucesso = gerContas.transfereValor(idConta3,  200.0, idConta2);
		saldoC3 = cc3.getSaldo();
		saldoC2 = cc2.getSaldo();
		assertFalse(semSucesso);		
		
		assertThat(saldoC3, is(-100.0));
		assertThat(saldoC2, is(0.0));
	}	

}
