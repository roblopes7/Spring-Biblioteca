package com.spring.biblioteca.service;

import com.spring.biblioteca.domains.Estante;
import com.spring.biblioteca.repositories.EstanteRepository;
import com.spring.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstanteService {

    @Autowired
    EstanteRepository repository;

    public Estante find(short id){
        Optional<Estante> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Estante.class.getName()));
    }

    public Estante insert(Estante obj) {
        return repository.saveAndFlush(obj);
    }

    public Estante update(Estante obj) {
        return repository.saveAndFlush(obj);
    }

    public void delete(short id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir uma estante que possui vinculos.");
        }
    }

    public List<Estante> findAll() {
        return repository.findAll();
    }

}
