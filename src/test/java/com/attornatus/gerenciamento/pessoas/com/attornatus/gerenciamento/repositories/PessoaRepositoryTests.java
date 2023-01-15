package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Pessoa;

@DataJpaTest
public class PessoaRepositoryTests {
	
	@Autowired
	private PessoaRepository repository;
	
	private long existingId;
	private long nonExistingId;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
	}
	
	@Test
	public void findByIdShouldReturnNonEmpityOptionalPessoaWhenIdExists() {
		
		Optional<Pessoa> result = repository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmpityOptionalPessoaWhenIdDoesNotExist() {
		
		Optional<Pessoa> result = repository.findById(nonExistingId);
		Assertions.assertTrue(result.isEmpty());
	}
	
}
