package com.example.app;

import io.micronaut.context.annotation.Context;
import io.micronaut.runtime.Micronaut;

@Context
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
