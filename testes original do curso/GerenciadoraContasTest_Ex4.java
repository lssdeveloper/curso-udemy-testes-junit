package com.lssdeveloper.teste.negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lssdeveloper.negocio.ContaCorrente;
import com.lssdeveloper.negocio.GerenciadoraContas;

/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre contas, realizadas pela classe {@link GerenciadoraContas}.
 * 
 * @author Gustavo Farias
 * @date 21/01/2035 
 */
public class GerenciadoraContasTest_Ex4 {

	private GerenciadoraContas gerContas;
	
	/**
	 * Teste b�sico da transfer�ncia de um valor da conta de um cliente para outro,
	 * estando ambos os clientes ativos e havendo saldo suficiente para tal transfer�ncia
	 * ocorrer com sucesso.
	 * 
	 * @author Gustavo Farias
	 * @date 21/01/2035
	 */
	@Test
	public void testTransfereValor() {

		/* ========== Montagem do cen�rio ========== */
		
		// criando alguns clientes
		ContaCorrente conta01 = new ContaCorrente(1, 200, true);
		ContaCorrente conta02 = new ContaCorrente(2, 0, true);
		
		// inserindo os clientes criados na lista de clientes do banco
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contasDoBanco);

		/* ========== Execu��o ========== */
		boolean sucesso = gerContas.transfereValor(1, 100, 2);
		
		/* ========== Verifica��es ========== */
		assertTrue(sucesso);
		assertThat(conta02.getSaldo(), is(100.0));
	}

}

// Documenta��o e coment�rios