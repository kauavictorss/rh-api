package rh.system.api.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DtoConta(
        @NotBlank
        @Pattern(regexp = "\\d{8}-\\d", message = "Conta deve estar no formato 00000000-0")
        String numConta,

        @NotBlank
        @Pattern(regexp = "\\d{4}", message = "Agência deve estar no formato 0000")
        String agencia,

        @NotNull
        ContaTipo tipoConta,

        @NotNull
        @Pattern(regexp = "\\d+(\\.\\d{1,2})?", message = "Salário deve ser um número válido")
        Double salario) {
}
