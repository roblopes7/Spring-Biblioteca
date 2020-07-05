package com.spring.biblioteca.service;

import com.spring.biblioteca.domains.Pessoa;
import com.spring.biblioteca.repositories.PessoaRepository;
import com.spring.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public Pessoa find(Integer id){
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Pessoa.class.getName()));
    }

    public Pessoa insert(Pessoa obj) {
        return repository.saveAndFlush(obj);
    }

    public Pessoa update(Pessoa obj) {
        return repository.saveAndFlush(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir uma Pessoa que possui vinculos.");
        }
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

}
