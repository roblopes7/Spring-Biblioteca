package com.spring.biblioteca.repositories;

import com.spring.biblioteca.domains.Estante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstanteRepository extends JpaRepository<Estante, Short> {
}
