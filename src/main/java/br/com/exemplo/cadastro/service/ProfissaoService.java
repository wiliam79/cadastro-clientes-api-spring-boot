package br.com.exemplo.cadastro.service;
import br.com.exemplo.cadastro.dto.*;
import br.com.exemplo.cadastro.entity.Profissao;
import br.com.exemplo.cadastro.exception.*;
import br.com.exemplo.cadastro.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service public class ProfissaoService {
private final ProfissaoRepository repository;
public ProfissaoService(ProfissaoRepository repository){this.repository=repository;}
@Transactional public ProfissaoResponse criar(ProfissaoRequest request){String nome=request.nome().trim();if(repository.existsByNomeIgnoreCase(nome))throw new ConflitoException("Profissao ja cadastrada");return converter(repository.save(new Profissao(nome)));}
@Transactional(readOnly=true) public List<ProfissaoResponse> listar(){return repository.findAll().stream().map(this::converter).toList();}
@Transactional(readOnly=true) public ProfissaoResponse buscar(Long id){return converter(obter(id));}
@Transactional(readOnly=true) public Profissao obter(Long id){return repository.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Profissao nao encontrada: "+id));}
private ProfissaoResponse converter(Profissao p){return new ProfissaoResponse(p.getId(),p.getNome());}
}
