package com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attornatus.gerenciamento.pessoas.com.attornatus.gerenciamento.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	@Query("SELECT obj FROM Endereco obj WHERE obj.pessoa.id = :id_pes ORDER BY logradouro")
	List<Endereco> findAllByPessoa(@Param(value = "id_pes") Long id_pes);

}
