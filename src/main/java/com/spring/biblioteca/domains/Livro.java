package com.spring.biblioteca.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Livro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String editora;
    private Integer ano;
    @ManyToOne
    @JoinColumn(name = "fk_estante", nullable = false)
    private Estante estante;
    @ManyToMany(mappedBy = "livros")
    private List<Autor> autors = new ArrayList<>();
    @OneToMany(mappedBy = "livro")
    private List<Exemplar> exemplares = new ArrayList<>();

    public Livro() {
    }

    public Livro(Integer id, String titulo, String editora, Integer ano, Estante estante) {
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
        this.estante = estante;
    }
}
