package com.spring.biblioteca.domains;

import com.spring.biblioteca.domains.utils.Estados;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Estados estado;

    public Cidade(Cidade obj) {
        this.id = obj.id;
        this.estado = obj.getEstado();
        this.nome = obj.getNome();
    }
}
