package br.com.exemplo.cadastro.repository;

import br.com.exemplo.cadastro.entity.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
