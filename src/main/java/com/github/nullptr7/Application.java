package com.github.nullptr7;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
        info = @Info(
                title = "Covid 19 Service",
                version = "1.0",
                description = "Service created to return Covid-19 details as an aggregated response",
                license = @License(name = "Apache 2.0", url = "https://www.bitbucket.org/ishan-shah"),
                contact = @Contact(url = "https://www.bitbucket.org/ishan-shah", name = "Ishan Shah", email = "ishannshah@gmail.com")
        )
)
public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
