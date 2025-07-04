package rh.api.funcionario.dto;

import jakarta.validation.constraints.NotBlank;
import rh.api.conta.DtoConta;
import rh.api.endereco.DtoEndereco;
import rh.api.funcionario.model.Especialidade;

public record DtoAtualizarFuncionario(@NotBlank String cpf, String nome, Integer idade, String email, Especialidade especialidade, DtoConta conta, DtoEndereco endereco) {
}
