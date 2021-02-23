package com.spring.biblioteca.service;

import com.spring.biblioteca.domains.Responsavel;
import com.spring.biblioteca.repositories.ResponsavelRepository;
import com.spring.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelService {

    @Autowired
    ResponsavelRepository repository;

    public Responsavel find(Integer id){
        Optional<Responsavel> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Responsavel.class.getName()));
    }

    public Responsavel insert(Responsavel obj) {
        return repository.saveAndFlush(obj);
    }

    public Responsavel update(Responsavel obj) {
        return repository.saveAndFlush(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir um Responsavel que possui vinculos.");
        }
    }

    public List<Responsavel> findAll() {
        return repository.findAll();
    }

}
