package rh.system.api.funcionario.dto;

import jakarta.validation.constraints.NotBlank;
import rh.system.api.conta.DtoConta;
import rh.system.api.endereco.DtoEndereco;
import rh.system.api.funcionario.Especialidade;

public record DtoAtualizarFuncionario(@NotBlank String cpf, String nome, Integer idade, String email, Especialidade especialidade, DtoConta conta, DtoEndereco endereco) {
}
