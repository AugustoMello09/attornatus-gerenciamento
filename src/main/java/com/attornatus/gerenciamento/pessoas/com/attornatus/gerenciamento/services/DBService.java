package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.services;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Endereco;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Pessoa;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.repositories.EnderecoRepository;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.repositories.PessoaRepository;

@Service
public class DBService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public void instanciaBaseDedados() {
		Pessoa p1 = new Pessoa(null, "Popor", LocalDate.parse("1983-06-21"));
		Pessoa p2 = new Pessoa(null, "Byecoise", LocalDate.parse("2000-07-10"));
		Pessoa p3 = new Pessoa(null, "Nixul", LocalDate.parse("2001-06-04"));
		
		Endereco end1 = new Endereco(null, "Rua Lopes", "17568429", 736, "São Paulo", p1);
		Endereco end2 = new Endereco(null, "Rua Flores", "86549723", 423, "Rio de Janeiro", p2);
		Endereco end3 = new Endereco(null, "Rua Pereira", "32152468", 789, "Porto Alegre", p3);
		Endereco end4 = new Endereco(null, "Rua Afonso", "76985245", 234, "Porto Alegre", p1);
		
		
		
		p1.getEnderecos().addAll(Arrays.asList(end1));
		p1.getEnderecos().addAll(Arrays.asList(end4));
		
		p2.getEnderecos().addAll(Arrays.asList(end2));
		p3.getEnderecos().addAll(Arrays.asList(end3));
		
		this.pessoaRepository.saveAll(Arrays.asList(p1, p2, p3));
		this.enderecoRepository.saveAll(Arrays.asList(end1,end2, end3, end4));
	}
}
