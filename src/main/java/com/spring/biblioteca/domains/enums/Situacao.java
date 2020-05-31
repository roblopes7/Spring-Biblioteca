package com.spring.biblioteca.domains.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public enum Situacao {

    DISPONIVEL(1, "Disponível"),
    EMPRESTADO(2, "Emprestado");

    private int id;
    private String descricao;

    Situacao(int i, String disponível) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Situacao toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Situacao x : Situacao.values()) {
            if (codigo.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido " + codigo);
    }
}
