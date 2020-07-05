package com.spring.biblioteca.dto;

import com.spring.biblioteca.domains.Autor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Setter
@Getter
public class AutorDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Nome do autor não pode estar em branco.")
    @Length(min = 3, max = 125, message = "Nome do autor deve ter entre 3 e 125 caracteres.")
    private String nome;

    public AutorDTO() {
    }

    public AutorDTO(Autor obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }
}
