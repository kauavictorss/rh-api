package rh.system.api.conta;

import jakarta.persistence.Embeddable;
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
    private String tipoConta;
    private Double salario;

    public Conta(DtoConta dados) {
        this.numConta = dados.numConta();
        this.agencia = dados.agencia();
        this.tipoConta = dados.tipoConta();
        this.salario = dados.salario();
    }

}
