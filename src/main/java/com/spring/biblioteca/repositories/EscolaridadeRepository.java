package com.spring.biblioteca.repositories;

import com.spring.biblioteca.domains.Escolaridade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscolaridadeRepository extends JpaRepository<Escolaridade, Integer> {
}
