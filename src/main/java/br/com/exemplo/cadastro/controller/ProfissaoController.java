package br.com.exemplo.cadastro.controller;
import br.com.exemplo.cadastro.dto.*;
import br.com.exemplo.cadastro.service.ProfissaoService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;import java.util.List;
@RestController @RequestMapping("/profissoes") public class ProfissaoController {
private final ProfissaoService service;public ProfissaoController(ProfissaoService service){this.service=service;}
@PostMapping public ResponseEntity<ProfissaoResponse> criar(@Valid @RequestBody ProfissaoRequest req){ProfissaoResponse p=service.criar(req);URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.id()).toUri();return ResponseEntity.created(uri).body(p);}
@GetMapping public List<ProfissaoResponse> listar(){return service.listar();}
@GetMapping("/{id}") public ProfissaoResponse buscar(@PathVariable Long id){return service.buscar(id);}
}
