package com.spring.biblioteca.service;

import com.spring.biblioteca.domains.Autor;
import com.spring.biblioteca.dto.AutorDTO;
import com.spring.biblioteca.repositories.AutorRepository;
import com.spring.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    AutorRepository repository;

    public Autor find(Integer id){
        Optional<Autor> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Autor.class.getName()));
    }

    public Autor insert(Autor obj) {
        return repository.saveAndFlush(obj);
    }

    public Autor update(Autor obj) {
        Autor newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.saveAndFlush(newObj);
    }

    private void updateData(Autor newObj, Autor obj) {
        newObj.setNome(obj.getNome());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir um autor que possui vinculos.");
        }
    }

    public List<Autor> findAll() {
        return repository.findAll();
    }

    public Autor fromDTO(AutorDTO dto){
        return new Autor(dto);
    }

}
