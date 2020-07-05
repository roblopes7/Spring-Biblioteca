package com.spring.biblioteca.controller;

import com.spring.biblioteca.domains.Autor;
import com.spring.biblioteca.dto.AutorDTO;
import com.spring.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Autor> find(@PathVariable Integer id) {
        Autor obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody AutorDTO dto) {
        Autor obj = service.fromDTO(dto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody AutorDTO dto, @PathVariable Integer id) {
        find(id);
        Autor obj = service.fromDTO(dto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateForIdObj(@Valid @RequestBody AutorDTO dto) {
        find(dto.getId());
        Autor obj = service.fromDTO(dto);
        obj.setId(dto.getId());
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AutorDTO>> findAll() {
        List<Autor> list = service.findAll();
        List<AutorDTO>listDTO = list.stream().map(obj -> new AutorDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
