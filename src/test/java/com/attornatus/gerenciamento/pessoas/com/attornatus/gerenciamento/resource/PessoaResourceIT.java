package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.dto.PessoaDTOWithAdress;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.test.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PessoaResourceIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Long existingId;
	private Long nonExistingId;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;

	}

	@Test
	public void findAllShouldReturnSortedPageWhenSortByNameAsc() throws Exception {

		ResultActions result = mockMvc
				.perform(get("/pessoas?page=0&size=12&sort=nome,asc").accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());

		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].nome").value("Byecoise"));
		result.andExpect(jsonPath("$.content[1].nome").value("Nixul"));
		result.andExpect(jsonPath("$.content[2].nome").value("Popor"));
	}

	@Test
	public void findAllShouldReturnSortedPageWhenSortByNameDesc() throws Exception {

		ResultActions result = mockMvc
				.perform(get("/pessoas?page=0&size=12&sort=nome,desc").accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());

		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].nome").value("Popor"));
		result.andExpect(jsonPath("$.content[1].nome").value("Nixul"));
		result.andExpect(jsonPath("$.content[2].nome").value("Byecoise"));
	}

	@Test
	public void updateShouldReturnPessoaDTOWhenIdExists() throws Exception {

		PessoaDTOWithAdress pessoaDTO = Factory.createdPessoaDTO();
		String jsonBody = objectMapper.writeValueAsString(pessoaDTO);

		String expectedNome = pessoaDTO.getNome();

		ResultActions result = mockMvc.perform(put("/pessoas/{id}", existingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(existingId));
		result.andExpect(jsonPath("$.nome").value(expectedNome));

	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		
		PessoaDTOWithAdress pessoaDTO = Factory.createdPessoaDTO();
		String jsonBody = objectMapper.writeValueAsString(pessoaDTO);
		
		ResultActions result = 
				mockMvc.perform(put("/pessoas/{id}", nonExistingId)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}

}
