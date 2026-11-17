package br.com.exemplo.cadastro.entity;
import jakarta.persistence.*;
@Entity @Table(name="profissoes",uniqueConstraints=@UniqueConstraint(columnNames="nome"))
public class Profissao {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(nullable=false,length=100) private String nome;
protected Profissao() {}
public Profissao(String nome) { this.nome=nome; }
public Long getId(){return id;} public String getNome(){return nome;} public void setNome(String nome){this.nome=nome;}
}
