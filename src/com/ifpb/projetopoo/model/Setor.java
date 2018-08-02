package com.ifpb.projetopoo.model;

public enum Setor {

    atendimento(1),
    cozinha(2),
    caixa(3),
    gerencia(4);

    public final int codSetor;

    Setor (int codSetor){
        this.codSetor=codSetor;
    }

    public int getCodSetor() {
        return codSetor;
    }
}
