package com.spring.biblioteca.service;

import com.spring.biblioteca.domains.Escolaridade;
import com.spring.biblioteca.repositories.EscolaridadeRepository;
import com.spring.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

public class EscolaridadeService {

    @Autowired
    EscolaridadeRepository repository;

    public Escolaridade find(Integer id){
        Optional<Escolaridade> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Escolaridade.class.getName()));
    }

    public Escolaridade insert(Escolaridade obj) {
        return repository.saveAndFlush(obj);
    }

    public Escolaridade update(Escolaridade obj) {
        return repository.saveAndFlush(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir uma informação de escolaridade que possui vinculos.");
        }
    }

    public List<Escolaridade> findAll() {
        return repository.findAll();
    }

}
