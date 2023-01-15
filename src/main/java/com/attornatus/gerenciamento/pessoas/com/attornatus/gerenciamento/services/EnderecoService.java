package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Endereco;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Pessoa;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Transactional(readOnly = true)
	public List<Endereco> findAll(Long id_pes) {
		pessoaService.findById(id_pes);
		return enderecoRepository.findAllByPessoa(id_pes);
	}
	
	@Transactional
	public Endereco created(Long id_pes, Endereco obj) {
		obj.setId(null);
		Pessoa pes = pessoaService.findByIdUtils(id_pes);
		obj.setPessoa(pes);
		return enderecoRepository.save(obj);
	}

}
