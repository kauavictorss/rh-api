package rh.system.api.funcionario.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import rh.system.api.conta.Conta;
import rh.system.api.endereco.Endereco;
import rh.system.api.funcionario.Especialidade;
import rh.system.api.funcionario.Funcionario;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DtoDetalhamentoFuncionario(String cpf, String nome, Integer idade, String email, Especialidade especialidade, Conta conta, Endereco endereco) {
    public DtoDetalhamentoFuncionario (Funcionario funcionario) {
        this(funcionario.getCpf(), funcionario.getNome(), funcionario.getIdade(), funcionario.getEmail(), funcionario.getEspecialidade(), funcionario.getConta(), funcionario.getEndereco());
    }

    public DtoDetalhamentoFuncionario(String cpf, String nome, Especialidade especialidade, Conta conta) {
        this(cpf, nome, null, null, especialidade, conta, null);
    }
}
