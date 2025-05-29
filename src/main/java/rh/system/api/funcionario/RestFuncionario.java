package rh.system.api.funcionario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import rh.system.api.conta.ContaTipo;
import rh.system.api.funcionario.dto.DtoAtualizarFuncionario;
import rh.system.api.funcionario.dto.DtoCadastroFuncionario;
import rh.system.api.funcionario.dto.DtoListaFuncionario;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("funcionarios")
public class RestFuncionario {

    private final ValidadorCadFuncionario validador;
    private final RepoFuncionario repositorio;

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody @Valid DtoCadastroFuncionario dados) {
        log.info("Cadastrando funcionário: {}", dados);
        validador.validarCadastro(dados);
        repositorio.save(new Funcionario(dados));
        log.info("Funcionário cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public List<DtoListaFuncionario> listagemDeFuncionarios() {
        return repositorio.findAll().stream().map(DtoListaFuncionario::new).toList();
    }

    @GetMapping("/cpf/{cpf}")
    public List<DtoListaFuncionario> buscarPorCpf(@PathVariable String cpf) {
        return repositorio.findById(cpf).stream().map(DtoListaFuncionario::new).toList();
    }

    @GetMapping("/listar/{especialidade}")
    public List<DtoListaFuncionario> listarPorEspecialidade(@PathVariable String especialidade) {
        return repositorio.buscarPorEspecialidade(List.of(especialidade))
            .stream()
            .map(DtoListaFuncionario::new)
            .toList();
    }

    @PutMapping("/atualizar")
    @Transactional
    public void atualizarFuncionario(@RequestBody @Valid DtoAtualizarFuncionario dados) {
        var funcionario = repositorio.getReferenceById(dados.cpf());
        funcionario.atualizarDados(dados);
    }

    @GetMapping("/listar/conta/{tipoConta}")
    public List<DtoListaFuncionario> listarFuncionariosPorTipoConta(@PathVariable String tipoConta) {
        log.info("Listando funcionários por tipo de conta: {}", tipoConta);
        return repositorio.buscarPorTipoConta(List.of(ContaTipo.valueOf(tipoConta)))
            .stream()
            .map(DtoListaFuncionario::new)
            .toList();
    }
}
