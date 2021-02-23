package com.spring.biblioteca.services;

import com.spring.biblioteca.domains.Exemplar;
import com.spring.biblioteca.repositories.ExemplarRepository;
import com.spring.biblioteca.services.exceptions.DataIntegrityException;
import com.spring.biblioteca.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplarService {

    @Autowired
    private ExemplarRepository exemplarRepository;

    public Exemplar buscar(Integer id) {
        Optional<Exemplar> obj = exemplarRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Exemplar.class.getName()));
    }

    public Exemplar inserir(Exemplar obj) {
        obj.setId(null);
        return exemplarRepository.save(obj);
    }

    public Exemplar atualizar(Exemplar obj) {
        buscar(obj.getId());
        return exemplarRepository.save(obj);
    }

    public void remover(Integer id) {
        buscar(id);
        try {
            exemplarRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir este exemplar.");
        }
    }

    public List<Exemplar> carregarTodos() {
        return exemplarRepository.findAll();
    }

    public Page<Exemplar> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return exemplarRepository.findAll(pageRequest);
    }

}
