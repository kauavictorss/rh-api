package rh.system.api.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoFuncionario extends JpaRepository<Funcionario, String> {
}
