package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.dto.PessoaDTO;

@SpringBootTest
@Transactional
public class PessoaServiceTest {

	@Autowired
	private PessoaService service;

	@Test
	public void findAllPagedShouldReturnPageWhenPage0Size10() {

		PageRequest pageRequest = PageRequest.of(0, 10);

		Page<PessoaDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(0, result.getNumber());
		Assertions.assertEquals(10, result.getSize());

	}

	@Test
	public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {

		PageRequest pageRequest = PageRequest.of(50, 10);

		Page<PessoaDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortByName() {

		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("nome"));

		Page<PessoaDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals("Byecoise", result.getContent().get(0).getNome());
		Assertions.assertEquals("Nixul", result.getContent().get(1).getNome());
		Assertions.assertEquals("Popor", result.getContent().get(2).getNome());
	}
}
