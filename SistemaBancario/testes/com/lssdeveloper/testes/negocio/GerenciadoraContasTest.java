package com.lssdeveloper.testes.negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lssdeveloper.negocio.ContaCorrente;
import com.lssdeveloper.negocio.GerenciadoraContas;

public class GerenciadoraContasTest {
	
	private GerenciadoraContas gerContas;

	@Test
	public void testTransfereValor() {
		
		/*=========Montagem do cenário=========*/
		//criando contas
		ContaCorrente cc1 = new ContaCorrente(1, 200, true);
		ContaCorrente cc2 = new ContaCorrente(2, 0, true);
		
		//inserindo as contas na lista do banco
		List<ContaCorrente> lstContasDoBanco = new ArrayList<>();
		lstContasDoBanco.add(cc1);
		lstContasDoBanco.add(cc2);
		
		gerContas = new GerenciadoraContas(lstContasDoBanco);
		
		/*=========Execução==========*/
		boolean sucesso =  gerContas.transfereValor(1, 100, 2);
		boolean semSucesso = gerContas.transfereValor(2, 300, 1);
		
		/*=========Verificação========*/
		assertTrue(sucesso);
		assertThat(sucesso, is(true));
		assertFalse(semSucesso);
		assertThat(semSucesso, is(false));	
		
		//como o curso fez
		assertThat(cc1.getSaldo(), is(100));
		assertThat(cc2.getSaldo(), is(100));

	}

}
