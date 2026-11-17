package br.com.exemplo.cadastro.exception;
import java.time.Instant;
import java.util.*;
import org.springframework.http.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
@RestControllerAdvice public class GlobalExceptionHandler {
private ResponseEntity<Map<String,Object>> erro(HttpStatus status,String mensagem,HttpServletRequest req){return ResponseEntity.status(status).body(Map.of("timestamp",Instant.now().toString(),"status",status.value(),"erro",mensagem,"path",req.getRequestURI()));}
@ExceptionHandler(RecursoNaoEncontradoException.class) public ResponseEntity<Map<String,Object>> naoEncontrado(RecursoNaoEncontradoException e,HttpServletRequest r){return erro(HttpStatus.NOT_FOUND,e.getMessage(),r);}
@ExceptionHandler(ConflitoException.class) public ResponseEntity<Map<String,Object>> conflito(ConflitoException e,HttpServletRequest r){return erro(HttpStatus.CONFLICT,e.getMessage(),r);}
@ExceptionHandler(IllegalArgumentException.class) public ResponseEntity<Map<String,Object>> invalido(IllegalArgumentException e,HttpServletRequest r){return erro(HttpStatus.BAD_REQUEST,e.getMessage(),r);}
@ExceptionHandler(DataIntegrityViolationException.class) public ResponseEntity<Map<String,Object>> integridade(DataIntegrityViolationException e,HttpServletRequest r){return erro(HttpStatus.CONFLICT,"Operacao viola uma restricao do banco de dados",r);}
@ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<Map<String,Object>> validacao(MethodArgumentNotValidException e,HttpServletRequest r){
Map<String,String> campos=new LinkedHashMap<>();e.getBindingResult().getFieldErrors().forEach(f->campos.putIfAbsent(f.getField(),f.getDefaultMessage()));
Map<String,Object> body=new LinkedHashMap<>();body.put("timestamp",Instant.now().toString());body.put("status",400);body.put("erro","Dados invalidos");body.put("campos",campos);body.put("path",r.getRequestURI());return ResponseEntity.badRequest().body(body);}
}
