package com.spring.biblioteca.services;

import com.spring.biblioteca.domains.Estante;
import com.spring.biblioteca.repositories.EstanteRepository;
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
public class EstanteService {

    @Autowired
    private EstanteRepository estanteRepository;

    public Estante buscar(Short id) {
        Optional<Estante> obj = estanteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Estante.class.getName()));
    }

    public Estante inserir(Estante obj) {
        obj.setId(null);
        return estanteRepository.save(obj);
    }

    public Estante atualizar(Estante obj) {
        if(buscar(obj.getId()) != null){
            return estanteRepository.save(obj);
        }
        return null;
    }

    public void remover(Short id) {
        buscar(id);
        try {
            estanteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma estante que possui produtos");
        }
    }

    public List<Estante> carregarTodos() {
        return estanteRepository.findAll();
    }

    public Page<Estante> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return estanteRepository.findAll(pageRequest);
    }
}
