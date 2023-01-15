package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.dto.PessoaDTO;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.dto.PessoaDTOWithAdress;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.services.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/pessoas")
@Api(value = "API REST Pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {
	@Autowired
	private PessoaService service;

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna um usuário com seu respectivo endereço")
	public ResponseEntity<PessoaDTOWithAdress> findById(@PathVariable Long id) {
		PessoaDTOWithAdress obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de usuários paginados e com possíveis ordenações")
	public ResponseEntity<Page<PessoaDTO>> findAllPaged(Pageable pageable) {
		Page<PessoaDTO> entity = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(entity);
	}


	@PostMapping
	@ApiOperation(value = "Cria um usuário")
	public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Altera o usuário")
	public ResponseEntity<PessoaDTO> update(@PathVariable Long id,@Valid @RequestBody PessoaDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);

	}
	
	@PatchMapping(value = "/{id}")
	@ApiOperation(value = "Altera somente um atributo do usuário")
	public ResponseEntity<PessoaDTO> updatePatch(@PathVariable Long id,@Valid @RequestBody PessoaDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);

	}


}
