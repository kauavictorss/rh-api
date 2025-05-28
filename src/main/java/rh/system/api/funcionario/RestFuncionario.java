package rh.system.api.funcionario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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

    private final RepoFuncionario repositorio;

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody @Valid DtoCadastroFuncionario dados) {
        log.info("Cadastrando funcionário");
        if (dados.idade() < 18) {
            log.error("Erro ao cadastrar!");
            throw new RuntimeException("Funcionário menor de idade");
        } else {
            log.info("Funcionário maior de idade");
        }
        if (repositorio.existsById(dados.cpf())) {
            log.error("Erro ao cadastrar!");
            throw new RuntimeException("CPF já cadastrado");
        } else {
            log.info("CPF válido");
        }
        repositorio.save(new Funcionario(dados));
        log.info("Funcionário cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public List<DtoListaFuncionario> listagemDeFuncionarios() {
        return repositorio.findAll().stream().map(DtoListaFuncionario::new).toList();
    }

    @GetMapping("/listar/cpf/{cpf}")
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
}
