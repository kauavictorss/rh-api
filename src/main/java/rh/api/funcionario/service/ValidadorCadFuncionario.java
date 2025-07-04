package rh.api.funcionario.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rh.api.funcionario.RepoFuncionario;
import rh.api.funcionario.dto.DtoCadastroFuncionario;
import rh.api.funcionario.model.Especialidade;
import rh.api.funcionario.model.Funcionario;

@Component
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ValidadorCadFuncionario {

    private final RepoFuncionario repositorio;

    public void validarCadastro(DtoCadastroFuncionario dados) {
        if (dados.idade() < 18)
            throwErro("Funcionário menor de idade!");
        if (repositorio.existsById(dados.cpf()))
            throwErro("CPF já cadastrado!");
        if (repositorio.existsByContaNumConta(dados.conta().numConta()))
            throwErro("Número da conta pertence a outro funcionário!");
        if (repositorio.existsByEmail(dados.email()))
            throwErro("Email pertence a outro funcionário!");
    }

    public void throwErro(String mensagem) {
        log.error("Erro ao cadastrar! {}", mensagem);
        throw new RuntimeException(mensagem);
    }

    public void ajustarSalarioSeProgramador(Funcionario funcionario) {
        if (funcionario.getEspecialidade() == Especialidade.PROGRAMACAO) {
            var salarioAtual = funcionario.getConta().getSalario();
            var aumento = funcionario.getConta().getSalario() * 0.20;
            funcionario.getConta().setSalario(funcionario.getConta().getSalario() + aumento);
            log.info("Salário do programador {} aumentado em 20%: salário anterior = {}, novo salário = {}", funcionario.getNome(), salarioAtual, funcionario.getConta().getSalario());
        }
    }
}
