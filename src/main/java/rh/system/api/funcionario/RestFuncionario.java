package rh.system.api.funcionario;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
    public void cadastrar(@RequestBody DtoCadastroFuncionario dados) {
        log.info("Cadastrando funcionário");
        repositorio.save(new Funcionario(dados));
        log.info("Funcionário cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public List<DtoListaFuncionario> listar() {
        return repositorio.findAll().stream().map(DtoListaFuncionario::new).toList();
    }

    /*
    Métod no serviço para atualizar o salário
    public void atualizarSalario(int cpf, double novoSalario) {
    Funcionario funcionario = funcionarioRepository.findById(cpf)
        .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    funcionario.getConta().setSalario(novoSalario);
    funcionarioRepository.save(funcionario);
    }
    */
}
