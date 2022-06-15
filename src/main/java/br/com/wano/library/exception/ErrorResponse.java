package br.com.wano.library.exception;

import java.util.List;

public record ErrorResponse(List<Error> errors) {
}
