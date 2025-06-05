package rh.system.api.funcionario.dto;

import rh.system.api.conta.Conta;
import rh.system.api.endereco.Endereco;
import rh.system.api.funcionario.Especialidade;
import rh.system.api.funcionario.Funcionario;

public record DtoDetalhamentoFuncionario(String cpf, String nome, Integer idade, String email, Especialidade especialidade, Conta conta, Endereco endereco) {
    public DtoDetalhamentoFuncionario (Funcionario funcionario) {
        this(funcionario.getCpf(), funcionario.getNome(), funcionario.getIdade(), funcionario.getEmail(), funcionario.getEspecialidade(), funcionario.getConta(), funcionario.getEndereco());
    }
}
