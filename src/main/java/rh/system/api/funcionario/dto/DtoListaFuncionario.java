package rh.system.api.funcionario.dto;

import rh.system.api.funcionario.Especialidade;
import rh.system.api.funcionario.Funcionario;

public record DtoListaFuncionario(String nome, Integer idade, String email, Especialidade especialidade) {

    public DtoListaFuncionario(Funcionario funcionario) {
        this(funcionario.getNome(), funcionario.getIdade(), funcionario.getEmail(), funcionario.getEspecialidade());
    }
}
