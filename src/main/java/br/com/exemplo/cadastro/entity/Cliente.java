package br.com.exemplo.cadastro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "documento"))
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String nome;
    @Column(nullable = false, length = 150)
    private String email;
    @Column(nullable = false, length = 20)
    private String telefone;
    @Column(nullable = false, length = 14)
    private String documento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissao_id", nullable = false)
    private Profissao profissao;

    protected Cliente() {
    }

    public Cliente(String nome, String email, String telefone, String documento, Profissao profissao) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.documento = documento;
        this.profissao = profissao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String x) {
        nome = x;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String x) {
        email = x;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String x) {
        telefone = x;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String x) {
        documento = x;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao x) {
        profissao = x;
    }
}
