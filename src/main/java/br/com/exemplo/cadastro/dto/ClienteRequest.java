package br.com.exemplo.cadastro.dto;
import jakarta.validation.constraints.*;
public record ClienteRequest(@NotBlank @Size(max=120) String nome,
@NotBlank @Email @Size(max=150) String email,
@NotBlank @Pattern(regexp="[0-9()+ .-]{10,20}",message="Telefone deve ter de 10 a 20 caracteres validos") String telefone,
@NotBlank String documento,
@NotNull @Positive Long profissaoId) {}
