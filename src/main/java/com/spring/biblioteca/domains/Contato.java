package com.spring.biblioteca.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = true)
    private String telefone;
    @Column(nullable = true)
    private String celular;
    @Column(nullable = true)
    private String email;
    @Column(nullable = true)
    private String redeSocial;
    @Column(nullable = true)
    private String outros;
    @ManyToOne
    @JoinColumn(name = "PESSOA_ID")
    private Pessoa pessoa;

}
