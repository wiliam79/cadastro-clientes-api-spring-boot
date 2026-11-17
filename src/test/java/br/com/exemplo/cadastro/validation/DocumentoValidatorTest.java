package br.com.exemplo.cadastro.validation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DocumentoValidatorTest {
@Test void aceitaCpfValido(){assertEquals("52998224725",DocumentoValidator.normalizarEValidar("529.982.247-25"));}
@Test void rejeitaCpfInvalido(){assertThrows(IllegalArgumentException.class,()->DocumentoValidator.normalizarEValidar("111.111.111-11"));}
@Test void aceitaCnpjValido(){assertEquals("11222333000181",DocumentoValidator.normalizarEValidar("11.222.333/0001-81"));}
@Test void rejeitaCnpjInvalido(){assertThrows(IllegalArgumentException.class,()->DocumentoValidator.normalizarEValidar("11.222.333/0001-82"));}
}
