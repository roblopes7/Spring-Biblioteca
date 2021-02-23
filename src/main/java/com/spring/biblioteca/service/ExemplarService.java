package com.spring.biblioteca.service;

import com.spring.biblioteca.domains.Exemplar;
import com.spring.biblioteca.repositories.ExemplarRepository;
import com.spring.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplarService {

    @Autowired
    ExemplarRepository repository;

    public Exemplar find(Integer id){
        Optional<Exemplar> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Exemplar.class.getName()));
    }

    public Exemplar insert(Exemplar obj) {
        return repository.saveAndFlush(obj);
    }

    public Exemplar update(Exemplar obj) {
        return repository.saveAndFlush(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir um Exemplar que possui vinculos.");
        }
    }

    public List<Exemplar> findAll() {
        return repository.findAll();
    }

}
