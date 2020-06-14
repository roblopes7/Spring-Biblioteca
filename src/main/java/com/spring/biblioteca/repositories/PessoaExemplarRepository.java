package com.spring.biblioteca.repositories;

import com.spring.biblioteca.domains.PessoaExemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaExemplarRepository extends JpaRepository<PessoaExemplar, Integer> {
}
