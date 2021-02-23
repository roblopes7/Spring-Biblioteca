package com.spring.biblioteca.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Responsavel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String documento;
    @Column(nullable = false)
    private String telefone;

    public Responsavel(Responsavel r) {
        this.id = r.getId();
        this.nome = r.getNome();
        this.documento = r.getDocumento();
        this.telefone = r.getTelefone();
    }
}
