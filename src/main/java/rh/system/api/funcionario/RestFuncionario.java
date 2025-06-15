package rh.system.api.funcionario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rh.system.api.conta.ContaTipo;
import rh.system.api.funcionario.dto.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("funcionarios")
public class RestFuncionario {

    private final ValidadorCadFuncionario validador;
    private final RepoFuncionario repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoDetalhamentoFuncionario> cadastrar(@RequestBody @Valid DtoCadastroFuncionario dados, UriComponentsBuilder uriBuilder) {
        log.info("Cadastrando funcionário: {}", dados);

        validador.validarCadastro(dados);
        var funcionario = new Funcionario(dados);
        validador.ajustarSalarioSeProgramador(funcionario);
        repositorio.save(funcionario);

        var uri = uriBuilder.path("/funcionarios/{cpf}").buildAndExpand(funcionario.getCpf()).toUri();

        log.info("Funcionário cadastrado com sucesso!");
        return ResponseEntity.created(uri).body(new DtoDetalhamentoFuncionario(funcionario));
    }

    @GetMapping("/listar/ativos")
    public Page<DtoListaFuncionarios> listagemDeFuncionariosAtivos(@PageableDefault(sort = "nome") Pageable paginacao) {
        return repositorio.findAllByAtivo(paginacao).map(DtoListaFuncionarios::new);
    }

    @GetMapping("/listar/inativos")
    public Page<DtoListaFuncionarios>listaDeFuncionariosInativos(@PageableDefault(sort = "nome") Pageable paginacao) {
        return repositorio.findAllByInativo(paginacao).map(DtoListaFuncionarios::new);
    }

    @GetMapping("/cpf/{cpf}")
    public List<DtoListaFuncionarios> buscarPorCpf(@PathVariable String cpf) {
        return repositorio.findById(cpf).stream().map(DtoListaFuncionarios::new).toList();
    }

    @GetMapping("/dados-conta/{cpf}")
    public List<DtoDetalhamentoFuncionario> buscarDadosContaFuncionario(@PathVariable String cpf) {
        log.info("Buscando dados da conta do funcionário com CPF: {}", cpf);
        var funcionario = repositorio.findById(cpf);
        return funcionario.stream()
            .map(f -> new DtoDetalhamentoFuncionario(f.getCpf(), f.getNome(), f.getEspecialidade(), f.getConta()))
            .toList();
    }

    @GetMapping("/listar/{especialidade}")
    public List<DtoListaFuncionarios> listarPorEspecialidade(@PathVariable String especialidade) {
        return repositorio.buscarPorEspecialidade(List.of(especialidade))
            .stream()
            .map(DtoListaFuncionarios::new)
            .toList();
    }

    @GetMapping("/listar/conta/{tipoConta}")
    public List<DtoListaFuncionarios> listarFuncionariosPorTipoConta(@PathVariable String tipoConta) {
        log.info("Listando funcionários por tipo de conta: {}", tipoConta);
        return repositorio.buscarPorTipoConta(List.of(ContaTipo.valueOf(tipoConta)))
            .stream()
            .map(DtoListaFuncionarios::new)
            .toList();
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DtoDetalhamentoFuncionario> atualizarFuncionario(@RequestBody @Valid DtoAtualizarFuncionario dados) {
        var funcionario = repositorio.getReferenceById(dados.cpf());
        funcionario.atualizarDados(dados);
        return ResponseEntity.ok(new DtoDetalhamentoFuncionario(funcionario));
    }

    @DeleteMapping("/remover/{cpf}")
    @Transactional
    public ResponseEntity<Void> removerFuncionario(@PathVariable String cpf) {
        var funcionario = repositorio.getReferenceById(cpf);
        funcionario.excuir();
        log.info("Removendo funcionário(a) {} com CPF: {}",funcionario.getNome(), cpf);
        log.info("Funcionário removido com sucesso!");
        return ResponseEntity.noContent().build();
    }
}
