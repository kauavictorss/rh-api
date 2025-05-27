package rh.system.api.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoFuncionario extends JpaRepository<Funcionario, String> {

    @Query("""
        select f
        from Funcionario f
        where f.especialidade in :especialidades
    """)
    List<Funcionario> buscarPorEspecialidade(List<String> especialidades);
}
