package com.spring.biblioteca.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sexo;
    @Column(nullable = false)
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private Date dataNascimento;
    @Column(nullable = false)
    private String ufNaturalidade;
    @ManyToOne
    @JoinColumn(name = "CIDADE_ID", nullable = false)
    private Cidade cidadeNaturalidade;
    @Column(nullable = true)
    private String rg;
    @Column(nullable = true)
    private String bolsaAux;
    @Column(nullable = true)
    private String numeroBolsaAux;
    @ManyToOne
    @JoinColumn(name = "RESPONSAVEL_ID", nullable = true)
    private Responsavel responsavel;

    public Pessoa(Pessoa p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.sexo = p.getSexo();
        this.cpf = p.getCpf();
        this.dataNascimento = p.getDataNascimento();
        this.ufNaturalidade = p.getUfNaturalidade();
        this.cidadeNaturalidade = p.getCidadeNaturalidade();
        this.rg = p.getRg();
        this.bolsaAux = p.getBolsaAux();
        this.numeroBolsaAux = p.getNumeroBolsaAux();
        this.responsavel = p.getResponsavel();
    }
}
