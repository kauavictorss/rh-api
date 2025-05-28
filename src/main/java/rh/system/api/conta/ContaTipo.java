package rh.system.api.conta;

import lombok.Getter;

@Getter
public enum ContaTipo {
    CORRENTE("Conta Corrente"),
    POUPANCA("Conta Poupança"),
    SALARIO("Conta Salário");

    private final String descricao;

    ContaTipo(String descricao) {
        this.descricao = descricao;
    }
}
