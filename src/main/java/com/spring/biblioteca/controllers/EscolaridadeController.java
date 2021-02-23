package com.spring.biblioteca.controllers;


import com.spring.biblioteca.domains.Escolaridade;
import com.spring.biblioteca.services.EscolaridadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/escolaridades")
public class EscolaridadeController {


    @Autowired
    private EscolaridadeService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Escolaridade> find(@PathVariable Integer id) {
        Escolaridade obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Escolaridade obj) {
        obj = service.inserir(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Escolaridade obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = service.atualizar(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Escolaridade>> findAll() {
        List<Escolaridade> list = service.carregarTodos();
        List<Escolaridade> listDto = list.stream().map(obj -> new Escolaridade(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<Escolaridade>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Escolaridade> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<Escolaridade> listDto = list.map(obj -> new Escolaridade(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
