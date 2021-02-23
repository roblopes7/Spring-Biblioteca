package com.spring.biblioteca.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String rua;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String numero;
    @ManyToOne
    @JoinColumn(name = "CIDADE_ID")
    private Cidade cidade;
    @ManyToOne
    @JoinColumn(name = "PESSOA_ID")
    private Pessoa pessoa;



}
