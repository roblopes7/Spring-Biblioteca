package com.spring.biblioteca.service;

import com.spring.biblioteca.domains.Cidade;
import com.spring.biblioteca.repositories.CidadeRepository;
import com.spring.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

public class CidadeService {

    @Autowired
    CidadeRepository repository;

    public Cidade find(Integer id){
        Optional<Cidade> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Cidade.class.getName()));
    }

    public Cidade insert(Cidade obj) {
        return repository.saveAndFlush(obj);
    }

    public Cidade update(Cidade obj) {
        Cidade newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.saveAndFlush(newObj);
    }

    private void updateData(Cidade newObj, Cidade obj) {
        newObj.setNome(obj.getNome());
        newObj.setEstado(obj.getEstado());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir um autor que possui vinculos.");
        }
    }

    public List<Cidade> findAll() {
        return repository.findAll();
    }

}
