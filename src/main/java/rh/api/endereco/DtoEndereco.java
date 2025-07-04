package rh.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DtoEndereco(
        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
        String cep,

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotBlank
        @Pattern(regexp = "[A-Z]{2}", message = "UF deve conter apenas letras maiúsculas")
        String uf,

        String numero,
        String complemento) {
}
