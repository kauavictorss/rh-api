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
import rh.system.api.funcionario.dto.DtoAtualizarFuncionario;
import rh.system.api.funcionario.dto.DtoCadastroFuncionario;
import rh.system.api.funcionario.dto.DtoDetalhamentoFuncionario;
import rh.system.api.funcionario.dto.DtoListaFuncionarios;

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
    public void atualizarFuncionario(@RequestBody @Valid DtoAtualizarFuncionario dados) {
        var funcionario = repositorio.getReferenceById(dados.cpf());
        funcionario.atualizarDados(dados);
    }

    @DeleteMapping("/remover/{cpf}")
    @Transactional
    public void removerFuncionario(@PathVariable String cpf) {
        log.info("Removendo funcionário com CPF: {}", cpf);
        var funcionario = repositorio.getReferenceById(cpf);
        funcionario.excuir();
        log.info("Funcionário removido com sucesso!");
    }
}
