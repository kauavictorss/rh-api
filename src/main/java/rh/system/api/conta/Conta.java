package rh.system.api.conta;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Conta {

    private String numConta;
    private String agencia;

    @Enumerated(EnumType.STRING)
    private ContaTipo tipoConta;

    private Double salario;

    public Conta(DtoConta dados) {
        this.numConta = dados.numConta();
        this.agencia = dados.agencia();
        this.tipoConta = dados.tipoConta();
        this.salario = dados.salario();
    }

    public void atualizarConta(DtoConta conta) {
        if (conta.numConta() != null) {
            this.numConta = conta.numConta();
        }
        if (conta.agencia() != null) {
            this.agencia = conta.agencia();
        }
        if (conta.tipoConta() != null) {
            this.tipoConta = conta.tipoConta();
        }
        if (conta.salario() != null) {
            this.salario = conta.salario();
        }
    }
}
