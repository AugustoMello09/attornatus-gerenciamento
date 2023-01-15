package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.dto.EnderecoDTO;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Endereco;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.services.EnderecoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value ="/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping//exemplo:  enderecos?pessoa=1
	@ApiOperation(value = "Retorna um usuário e seus respectivos endereços")
	public ResponseEntity<List<EnderecoDTO>> findAll(@RequestParam(value="pessoa", defaultValue="0") Long id_pes) {
		List<Endereco> list = enderecoService.findAll(id_pes);
		List<EnderecoDTO> dto = list.stream().map(x -> new EnderecoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping//exemplo:  enderecos?pessoa=4
	@ApiOperation(value = "Insere um novo Endereço na base de dados e define para seu usuário")
	public ResponseEntity<List<Endereco>> insertNewAdress(
			@Valid @RequestParam(value = "pessoa", defaultValue = "0") Long id_pes, 
			@RequestBody Endereco obj){
		Endereco  newObj = enderecoService.created(id_pes, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/enderecos/{id}")
                .buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
