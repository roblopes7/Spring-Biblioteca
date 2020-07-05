package com.spring.biblioteca.service;

import com.spring.biblioteca.domains.Contato;
import com.spring.biblioteca.repositories.ContatoRepository;
import com.spring.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

public class ContatoService {

    @Autowired
    ContatoRepository repository;

    public Contato find(Integer id){
        Optional<Contato> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Contato.class.getName()));
    }

    public Contato insert(Contato obj) {
        return repository.saveAndFlush(obj);
    }

    public Contato update(Contato obj) {
        return repository.saveAndFlush(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir um autor que possui vinculos.");
        }
    }

    public List<Contato> findAll() {
        return repository.findAll();
    }

}
