package rh.system.api.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rh.system.api.conta.ContaTipo;

import java.util.List;

public interface RepoFuncionario extends JpaRepository<Funcionario, String> {

    @Query("""
        select f
        from Funcionario f
        where f.especialidade in :especialidades
    """)
    List<Funcionario> buscarPorEspecialidade(List<String> especialidades);

    @Query("""
        select f
        from Funcionario f
        where f.conta.tipoConta in :tipoConta
    """)
    List<Funcionario> buscarPorTipoConta(List<ContaTipo> tipoConta);

    boolean existsByContaNumConta(String numConta);

    boolean existsByEmail(String email);
}
