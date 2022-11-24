package com.example.app.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {AccountNotFoundException.class, ExceptionHandler.class})
public class AccountExceptionHandler implements ExceptionHandler<AccountNotFoundException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, AccountNotFoundException exception) {
        ErrorMessage message = ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.getCode())
                .message(exception.getMessage())
                .build();
        return HttpResponse.serverError(message)
                .status(HttpStatus.NOT_FOUND);
    }
}
