package rh.system.api.funcionario.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import rh.system.api.conta.ContaTipo;
import rh.system.api.funcionario.Especialidade;
import rh.system.api.funcionario.Funcionario;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DtoListaFuncionario(String cpf, String nome, Integer idade, String email, Especialidade especialidade, ContaTipo tipoConta) {

    public DtoListaFuncionario(Funcionario funcionario) {
        this(funcionario.getCpf(), funcionario.getNome(), funcionario.getIdade(), funcionario.getEmail(), funcionario.getEspecialidade(), null);
    }
}
