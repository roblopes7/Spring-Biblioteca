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
public class Escolaridade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = true)
    private String instituicao;
    @Column(nullable = true)
    private String nivel;
    @Column(nullable = true)
    private String periodo;
    @ManyToOne
    @JoinColumn(name = "PESSOA_ID")
    private Pessoa pessoa;

    public Escolaridade(Escolaridade e) {
        this.id = e.getId();
        this.instituicao = e.getInstituicao();
        this.nivel = e.getNivel();
        this.periodo = e.getPeriodo();
        this.pessoa = e.getPessoa();
    }
}
