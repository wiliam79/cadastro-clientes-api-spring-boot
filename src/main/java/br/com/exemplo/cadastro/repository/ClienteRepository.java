package br.com.exemplo.cadastro.repository;
import br.com.exemplo.cadastro.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<Cliente,Long> { boolean existsByDocumento(String documento); boolean existsByDocumentoAndIdNot(String documento,Long id); }
