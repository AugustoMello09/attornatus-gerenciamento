
package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Endereco;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTOWithAdress implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(max = 60, message = "O nome só pode ter no máximo 60 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	
	
	@PastOrPresent(message = "A data de nascimento não pode ser futura")
	private LocalDate dataDeNascimento;

	private List<EnderecoDTO> enderecos = new ArrayList<>();

	public PessoaDTOWithAdress(Long id, String nome, LocalDate dataDeNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;

	}

	public PessoaDTOWithAdress(Pessoa entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.dataDeNascimento = entity.getDataDeNascimento();
	}

	public PessoaDTOWithAdress(Pessoa entity, Set<Endereco> enderecos) {
		this(entity);
		enderecos.forEach(end -> this.enderecos.add(new EnderecoDTO(end)));
	}
}
