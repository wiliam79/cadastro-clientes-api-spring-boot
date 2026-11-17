package br.com.exemplo.cadastro.dto;

public record ClienteResponse(Long id, String nome, String email, String telefone, String documento, Long profissaoId,
                              String profissaoNome) {
}
