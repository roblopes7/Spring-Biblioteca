package com.spring.biblioteca.repositories;

import com.spring.biblioteca.domains.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {
}
