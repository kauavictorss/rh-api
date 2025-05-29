package rh.system.api.funcionario;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rh.system.api.funcionario.dto.DtoCadastroFuncionario;

@Component
@Slf4j
@RequiredArgsConstructor
public class ValidadorCadFuncionario {

    private final RepoFuncionario repositorio;

    public void validarCadastro(DtoCadastroFuncionario dados) {
        if (dados.idade() < 18)
            throwErro("Funcionário menor de idade");
        if (repositorio.existsById(dados.cpf()))
            throwErro("CPF já cadastrado");
        if (repositorio.existsByContaNumConta(dados.conta().numConta()))
            throwErro("Número da conta pertence a outro funcionário");
        if (repositorio.existsByEmail(dados.email()))
            throwErro("Email pertence a outro funcionário");
    }

    public void throwErro(String mensagem) {
        log.error("Erro ao cadastrar! {}", mensagem);
        throw new RuntimeException(mensagem);
    }
}
