package com.spring.biblioteca.services;

import com.spring.biblioteca.domains.Pessoa;
import com.spring.biblioteca.repositories.PessoaRepository;
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
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa buscar(Integer id) {
        Optional<Pessoa> obj = pessoaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
    }

    public Pessoa inserir(Pessoa obj) {
        obj.setId(null);
        return pessoaRepository.save(obj);
    }

    public Pessoa atualizar(Pessoa obj) {
        buscar(obj.getId());
        return pessoaRepository.save(obj);
    }

    public void remover(Integer id) {
        buscar(id);
        try {
            pessoaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esta pessoa");
        }
    }

    public List<Pessoa> carregarTodos() {
        return pessoaRepository.findAll();
    }

    public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return pessoaRepository.findAll(pageRequest);
    }

    private void updateData(Pessoa newObj, Pessoa obj) {
        newObj.setNome(obj.getNome());
    }
}
