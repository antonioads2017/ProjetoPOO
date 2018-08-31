package com.ifpb.projetopoo.model;

public enum Setor {

    ATENDIMENTO(1),
    COZINHA(2),
    CAIXA(3),
    GERENCIA(4);

    public final int codSetor;

    Setor (int codSetor){
        this.codSetor=codSetor;
    }

    public int getCodSetor() {
        return codSetor;
    }
}
