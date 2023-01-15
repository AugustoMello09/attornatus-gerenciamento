package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.test;

import java.time.LocalDate;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.dto.PessoaDTOWithAdress;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Endereco;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Pessoa;

public class Factory {

	public static Pessoa createdPessoa() {
		Pessoa pessoa = new Pessoa(1L, "Testador", LocalDate.parse("2000-01-01"));
		pessoa.getEnderecos().add(new Endereco(1L, "Rua Lopes", "17568429", 166, "São Paulo", pessoa));
		return pessoa;
	}

	public static PessoaDTOWithAdress createdPessoaDTO() {
		Pessoa pessoa = createdPessoa();
		return new PessoaDTOWithAdress(pessoa, pessoa.getEnderecos());
	}

}
