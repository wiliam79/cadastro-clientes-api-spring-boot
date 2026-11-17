package br.com.exemplo.cadastro.service;

import br.com.exemplo.cadastro.dto.*;
import br.com.exemplo.cadastro.entity.*;
import br.com.exemplo.cadastro.exception.*;
import br.com.exemplo.cadastro.repository.*;
import br.com.exemplo.cadastro.validation.DocumentoValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;
    private final ProfissaoService profissoes;

    public ClienteService(ClienteRepository repository, ProfissaoService profissoes) {
        this.repository = repository;
        this.profissoes = profissoes;
    }

    @Transactional
    public ClienteResponse criar(ClienteRequest req) {
        String documento = DocumentoValidator.normalizarEValidar(req.documento());
        if (repository.existsByDocumento(documento)) throw new ConflitoException("CPF/CNPJ ja cadastrado");
        Profissao p = profissoes.obter(req.profissaoId());
        Cliente c = new Cliente(req.nome().trim(), req.email().trim(), req.telefone().trim(), documento, p);
        return converter(repository.save(c));
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listar() {
        return repository.findAll().stream().map(this::converter).toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscar(Long id) {
        return converter(obter(id));
    }

    @Transactional
    public ClienteResponse atualizar(Long id, ClienteRequest req) {
        Cliente c = obter(id);
        String documento = DocumentoValidator.normalizarEValidar(req.documento());
        if (repository.existsByDocumentoAndIdNot(documento, id)) throw new ConflitoException("CPF/CNPJ ja cadastrado");
        Profissao p = profissoes.obter(req.profissaoId());
        c.setNome(req.nome().trim());
        c.setEmail(req.email().trim());
        c.setTelefone(req.telefone().trim());
        c.setDocumento(documento);
        c.setProfissao(p);
        return converter(c);
    }

    @Transactional
    public void excluir(Long id) {
        Cliente c = obter(id);
        repository.delete(c);
    }

    private Cliente obter(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente nao encontrado: " + id));
    }

    private ClienteResponse converter(Cliente c) {
        return new ClienteResponse(c.getId(), c.getNome(), c.getEmail(), c.getTelefone(), c.getDocumento(), c.getProfissao().getId(), c.getProfissao().getNome());
    }
}
