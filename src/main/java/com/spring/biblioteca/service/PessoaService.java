package com.spring.biblioteca.service;

import com.spring.biblioteca.domains.Livro;
import com.spring.biblioteca.repositories.LivroRepository;
import com.spring.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

public class LivroService {

    @Autowired
    LivroRepository repository;

    public Livro find(Integer id){
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Livro.class.getName()));
    }

    public Livro insert(Livro obj) {
        return repository.saveAndFlush(obj);
    }

    public Livro update(Livro obj) {
        return repository.saveAndFlush(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir uma Livro que possui vinculos.");
        }
    }

    public List<Livro> findAll() {
        return repository.findAll();
    }

}
