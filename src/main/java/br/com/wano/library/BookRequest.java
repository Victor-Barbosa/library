package br.com.wano.library;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record BookRequest (

    @NotBlank
    String titulo,
    @NotNull
    BigDecimal preco,
    String isbn
) {}
