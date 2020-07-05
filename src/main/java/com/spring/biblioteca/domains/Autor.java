package com.spring.biblioteca.domains;

import com.spring.biblioteca.dto.AutorDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @ManyToMany
    @JoinTable(name = "AUTOR_LIVRO", joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id"))
    List<Livro> livros = new ArrayList<>();

    public Autor() {
    }

    public  Autor(AutorDTO dto){
        this.id = dto.getId();
        this.nome = dto.getNome();
    }
}
