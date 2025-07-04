package rh.api.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rh.api.conta.ContaTipo;
import rh.api.funcionario.model.Funcionario;

import java.util.List;

public interface RepoFuncionario extends JpaRepository<Funcionario, String> {

    @Query("""
        select f
        from Funcionario f
        where f.ativo = true
        """)
    Page<Funcionario> findAllByAtivo(Pageable paginacao);

    @Query("""
        select f
        from Funcionario f
        where f.ativo = false
        """)
    Page<Funcionario> findAllByInativo(Pageable paginacao);

    @Query("""
        select f
        from Funcionario f
        where f.email = :email
    """)
    List<Funcionario> buscarPorEmail(String email);

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
