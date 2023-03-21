package com.starttech.api.de.eventos.entity;

public enum StatusEvento {
    DISPONIVEL("Aberto"),
    CANCELADO("Cancelado"),
    CONCLUIDO("Concluído");

    private final String descricao;

    StatusEvento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
