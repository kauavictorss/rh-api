package rh.system.api.funcionario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rh.system.api.conta.Conta;
import rh.system.api.endereco.Endereco;
import rh.system.api.funcionario.dto.DtoCadastroFuncionario;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    private String cpf;

    private String nome;
    private Integer idade;
    private String email;

    @Embedded
    @Valid
    private Conta conta;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    @Valid
    private Endereco endereco;

    public Funcionario(DtoCadastroFuncionario dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.email = dados.email();
        this.conta = new Conta(dados.conta());
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
}
