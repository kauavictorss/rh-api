package rh.api.funcionario.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rh.api.conta.Conta;
import rh.api.endereco.Endereco;
import rh.api.funcionario.dto.DtoAtualizarFuncionario;
import rh.api.funcionario.dto.DtoCadastroFuncionario;

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

    private Boolean ativo;

    public Funcionario(DtoCadastroFuncionario dados) {
        this.ativo = true;
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.email = dados.email();
        this.conta = new Conta(dados.conta());
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarDados(DtoAtualizarFuncionario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.idade() != null) {
            this.idade = dados.idade();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.especialidade() != null) {
            this.especialidade = dados.especialidade();
        }
        if (dados.conta() != null) {
            this.conta.atualizarConta(dados.conta());
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarEndereco(dados.endereco());
        }
    }

    public void excuir() {
        this.ativo = false;
    }
}
