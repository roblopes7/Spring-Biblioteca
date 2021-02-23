package com.spring.biblioteca.services;

import com.spring.biblioteca.domains.Livro;
import com.spring.biblioteca.repositories.LivroRepository;
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
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro buscar(Integer id) {
        Optional<Livro> obj = livroRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
    }

    public Livro inserir(Livro obj) {
        obj.setId(null);
        return livroRepository.save(obj);
    }

    public Livro atualizar(Livro obj) {
        buscar(obj.getId());
        return livroRepository.save(obj);
    }

    public void remover(Integer id) {
        buscar(id);
        try {
            livroRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma livro que possui produtos");
        }
    }

    public List<Livro> carregarTodos() {
        return livroRepository.findAll();
    }

    public Page<Livro> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return livroRepository.findAll(pageRequest);
    }

}
