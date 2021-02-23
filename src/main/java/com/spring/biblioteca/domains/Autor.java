package com.spring.biblioteca.domains;

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

    public Autor(Autor obj) {
        this.id = obj.getId();
        this.livros = obj.getLivros();
        this.nome = obj.getNome();
    }
}
