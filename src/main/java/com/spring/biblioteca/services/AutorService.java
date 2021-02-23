package com.spring.biblioteca.services;

import com.spring.biblioteca.domains.Autor;
import com.spring.biblioteca.repositories.AutorRepository;
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
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor buscar(Integer id) {
        Optional<Autor> obj = autorRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Autor.class.getName()));
    }

    public Autor inserir(Autor obj) {
        obj.setId(null);
        return autorRepository.save(obj);
    }

    public Autor atualizar(Autor obj) {
        buscar(obj.getId());
        return autorRepository.save(obj);
    }

    public void remover(Integer id) {
        buscar(id);
        try {
            autorRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma autor que possui produtos");
        }
    }

    public List<Autor> carregarTodos() {
        return autorRepository.findAll();
    }

    public Page<Autor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return autorRepository.findAll(pageRequest);
    }
}
