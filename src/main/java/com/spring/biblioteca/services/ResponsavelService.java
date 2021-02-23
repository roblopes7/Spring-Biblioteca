package com.spring.biblioteca.services;

import com.spring.biblioteca.domains.Responsavel;
import com.spring.biblioteca.repositories.ResponsavelRepository;
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
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public Responsavel buscar(Integer id) {
        Optional<Responsavel> obj = responsavelRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Responsavel.class.getName()));
    }

    public Responsavel inserir(Responsavel obj) {
        obj.setId(null);
        return responsavelRepository.save(obj);
    }

    public Responsavel atualizar(Responsavel obj) {
        buscar(obj.getId());
        return responsavelRepository.save(obj);
    }

    public void remover(Integer id) {
        buscar(id);
        try {
            responsavelRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir este responsavel;");
        }
    }

    public List<Responsavel> carregarTodos() {
        return responsavelRepository.findAll();
    }

    public Page<Responsavel> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return responsavelRepository.findAll(pageRequest);
    }

    private void updateData(Responsavel newObj, Responsavel obj) {
        newObj.setNome(obj.getNome());
    }
}
