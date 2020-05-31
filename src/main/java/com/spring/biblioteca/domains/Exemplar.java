package com.spring.biblioteca.domains;

import com.spring.biblioteca.domains.enums.Situacao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
public class Exemplar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Date dataAquisicao;
    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;
    @Column(nullable = false)
    private Integer situacao;

    public Exemplar() {
    }
}
