package com.spring.biblioteca.services;

import com.spring.biblioteca.domains.Escolaridade;
import com.spring.biblioteca.repositories.EscolaridadeRepository;
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
public class EscolaridadeService {

    @Autowired
    private EscolaridadeRepository escolaridadeRepository;

    public Escolaridade buscar(Integer id) {
        Optional<Escolaridade> obj = escolaridadeRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Escolaridade.class.getName()));
    }

    public Escolaridade inserir(Escolaridade obj) {
        obj.setId(null);
        return escolaridadeRepository.save(obj);
    }

    public Escolaridade atualizar(Escolaridade obj) {
        buscar(obj.getId());
        return escolaridadeRepository.save(obj);
    }

    public void remover(Integer id) {
        buscar(id);
        try {
            escolaridadeRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma escolaridade que possui produtos");
        }
    }

    public List<Escolaridade> carregarTodos() {
        return escolaridadeRepository.findAll();
    }

    public Page<Escolaridade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return escolaridadeRepository.findAll(pageRequest);
    }
}
