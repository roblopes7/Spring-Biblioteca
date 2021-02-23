package com.spring.biblioteca.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Estante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(nullable = false)
    private String descricao;



    public Estante(String descricao) {
        this.descricao = descricao;
    }

    public Estante(Estante obj) {
        this.id = obj.getId();
        this.descricao = obj.getDescricao();
    }
}
