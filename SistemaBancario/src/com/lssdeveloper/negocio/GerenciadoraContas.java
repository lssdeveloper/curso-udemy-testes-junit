package com.lssdeveloper.negocio;

import java.util.List;

/**
 * Classe de negócio para realizar operações sobre as contas do banco.
 */
public class GerenciadoraContas {

	private List<ContaCorrente> contasDoBanco;

	public GerenciadoraContas(List<ContaCorrente> contasDoBanco) {
		this.contasDoBanco = contasDoBanco;
	}

	/**
	 * Retorna uma lista com todas as contas do banco.
	 * @return lista com todas as contas do banco
	 */
	public List<ContaCorrente> getContasDoBanco() {
		return contasDoBanco;
	}

	/**
	 * Pesquisa por uma conta a partir do seu ID.
	 * @param idConta id da conta a ser pesquisada
	 * @return a conta pesquisada ou null, caso não seja encontrada
	 */
	public ContaCorrente pesquisaConta (int idConta) {

		for (ContaCorrente contaCorrente : contasDoBanco) {
			if(contaCorrente.getId() == idConta)
				return contaCorrente;
		}
		return null;
	}
	
	/**
	 * Adiciona uma nova conta na lista de contas do banco.
	 * @param novaConta é nova conta a ser adicionada
	 */
	public void adicionaConta (ContaCorrente novaConta) {
		this.contasDoBanco.add(novaConta);
	}

	/**
	 * Remove conta da lista de contas do banco.
	 * @param idConta ID da conta a ser removida 
	 * @return true se a conta foi removida. False, caso contrário.
	 */
	public boolean removeConta (int idConta) {
		
		boolean contaRemovida = false;
		
		for (int i = 0; i < contasDoBanco.size(); i++) {
			ContaCorrente conta = contasDoBanco.get(i);
			if(conta.getId() == idConta){
				contasDoBanco.remove(i);
				break;
			}
		}
		
		return contaRemovida;
	}

	/**
	 * Informa se uma determinada conta está ativa ou não.
	 * @param idConta ID da conta cujo status será verificado
	 * @return true se a conta está ativa. False, caso contrário. 
	 */
	public boolean contaAtiva (int idConta) {
		
		boolean contaAtiva = false;
		
		for (int i = 0; i < contasDoBanco.size(); i++) {
			ContaCorrente conta = contasDoBanco.get(i);
			if(conta.getId() == idConta)
				if(conta.isAtiva()){
					contaAtiva = true;
					break;
				}
		}
		
		return contaAtiva;
	}
	
	/**
	 * Transfere um determinado valor de uma conta Origem para uma conta Destino.
	 * Caso não haja saldo suficiente, o valor não ser transferido.
	 * 
	 * @param idContaOrigem conta que terá o valor deduzido
	 * @param valor valor a ser transferido
	 * @param idContaDestino conta que terá o valor acrescido
	 * @return true, se a transferência foi realizada com sucesso.
	 */
	public boolean transfereValor (int idContaOrigem, double valor, int idContaDestino) {
		
		boolean sucesso = false;
		
		ContaCorrente contaOrigem = pesquisaConta(idContaOrigem);
		ContaCorrente contaDestino = pesquisaConta(idContaDestino);
		
		if(contaOrigem.getSaldo() >= valor){
			contaDestino.setSaldo(contaDestino.getSaldo() + valor);
			contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
			sucesso = true;
		}
	
		return sucesso;
	}
	public void limpar(){
		this.contasDoBanco.clear();
	}
	
}
