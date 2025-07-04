package rh.api.conta;

import jakarta.validation.constraints.*;

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
        @DecimalMin(value = "0.0", inclusive = false, message = "Salário deve ser maior que 0(zero)!")
        @Digits(integer = 10, fraction = 2, message = "Salário pode ter no máximo duas casas decimais")
        Double salario) {
}
