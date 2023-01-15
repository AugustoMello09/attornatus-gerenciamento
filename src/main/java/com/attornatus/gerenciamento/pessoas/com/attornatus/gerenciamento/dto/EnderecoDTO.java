package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(min = 5, max = 40, message = "Deve ter entre 5 a 40 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String logradouro;
	
	// 19700.754
	@Size(max = 8, message = "Deve ter 8 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String cep;
	
	@NotBlank(message = "Campo obrigatório")
	@Positive(message = "Esse campo não pode ser negativo")
	private int numero;
	
	@Size(max = 28, message = "Deve ter 28 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String cidade;
	
	public EnderecoDTO() {
	}

	public EnderecoDTO(Endereco entity) {
		this.id = entity.getId();
		this.logradouro = entity.getLogradouro();
		this.cep = entity.getCep();
		this.numero = entity.getNumero();
		this.cidade = entity.getCidade();
	}

	public EnderecoDTO(Long id, String logradouro, String cep, int numero, String cidade) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
	}

}
