package com.spring.biblioteca.services;

import com.spring.biblioteca.domains.Cidade;
import com.spring.biblioteca.repositories.CidadeRepository;
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
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade buscar(Integer id) {
        Optional<Cidade> obj = cidadeRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
    }

    public Cidade inserir(Cidade obj) {
        obj.setId(null);
        return cidadeRepository.save(obj);
    }

    public Cidade atualizar(Cidade obj) {
        buscar(obj.getId());
        return cidadeRepository.save(obj);
    }

    public void remover(Integer id) {
        buscar(id);
        try {
            cidadeRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cidade que possui produtos");
        }
    }

    public List<Cidade> carregarTodos() {
        return cidadeRepository.findAll();
    }

    public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return cidadeRepository.findAll(pageRequest);
    }

}
