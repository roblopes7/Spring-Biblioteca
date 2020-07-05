package com.spring.biblioteca.service;

import com.spring.biblioteca.domains.Endereco;
import com.spring.biblioteca.repositories.EnderecoRepository;
import com.spring.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public Endereco find(Integer id){
        Optional<Endereco> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Endereco.class.getName()));
    }

    public Endereco insert(Endereco obj) {
        return repository.saveAndFlush(obj);
    }

    public Endereco update(Endereco obj) {
        return repository.saveAndFlush(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir um endereco que possui vinculos.");
        }
    }

    public List<Endereco> findAll() {
        return repository.findAll();
    }

}
