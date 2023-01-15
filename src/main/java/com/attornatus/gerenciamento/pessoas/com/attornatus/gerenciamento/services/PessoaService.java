
package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.controllers.exceptions.ResourceNotFoundException;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.dto.PessoaDTO;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.dto.PessoaDTOWithAdress;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Pessoa;
import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional(readOnly = true)
	public PessoaDTOWithAdress findById(Long id) {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		Pessoa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
		return new PessoaDTOWithAdress(entity, entity.getEnderecos());
	}

	@Transactional(readOnly = true)
	public Page<PessoaDTO> findAllPaged(Pageable pageable) {
		Page<Pessoa> list = pessoaRepository.findAll(pageable);
		return list.map(x -> new PessoaDTO(x));
	}

	@Transactional(readOnly = true)
	public PessoaDTO insert(PessoaDTO dto) {
		Pessoa entity = new Pessoa();
		entity.setNome(dto.getNome());
		entity.setDataDeNascimento(dto.getDataDeNascimento());
		entity = pessoaRepository.save(entity);
		return new PessoaDTO(entity);
	}

	@Transactional
	public PessoaDTO update(Long id, PessoaDTO dto) {
		try {
			Pessoa entity = pessoaRepository.getOne(id);
			entity.setNome(dto.getNome());
			entity.setDataDeNascimento(dto.getDataDeNascimento());
			entity = pessoaRepository.save(entity);
			return new PessoaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not Found" + id);
		}
	}
	
	
	public Pessoa findByIdUtils(Long id) {
		Optional<Pessoa> find = pessoaRepository.findById(id);
		return find.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));

	}

}
