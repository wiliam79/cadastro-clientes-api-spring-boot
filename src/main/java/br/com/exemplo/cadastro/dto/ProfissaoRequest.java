package br.com.exemplo.cadastro.dto;

import jakarta.validation.constraints.*;

public record ProfissaoRequest(@NotBlank @Size(max = 100) String nome) {
}
