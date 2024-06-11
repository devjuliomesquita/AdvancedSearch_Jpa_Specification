package com.juliomesquita.application.infra.configurations;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApi30Config {
    private final String moduleName;
    private final String apiVersion;

    public OpenApi30Config(
            @Value("${module-name}") String moduleName,
            @Value("${api-version}") String apiVersion) {
        this.moduleName = moduleName;
        this.apiVersion = apiVersion;
    }

    @Bean
    public OpenAPI customOpenAPI() {

        final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
        return new OpenAPI()
                .info(new Info().
                        title(apiTitle)
                        .version(apiVersion)
                        .summary("API JPA Specification")
                        .description(new StringBuilder()
                                .append("Esta API foi desenvolvida para gerenciar um conjunto limitado de entidades e permitir a execução de consultas customizadas definidas pelo usuário. Utilizando JPA Specifications e Criteria API, a API oferece uma forma flexível e robusta de criar e testar consultas dinâmicas. Isso possibilita aos usuários definir critérios de busca complexos e executar consultas de maneira eficiente e segura.")
                                .append("\n\n")
                                .append("O principal objetivo da API é proporcionar um ambiente onde consultas customizadas podem ser facilmente criadas e testadas sem a necessidade de modificar o código base. Com uma interface RESTful, a API facilita a interação e manipulação de dados, permitindo operações CRUD básicas e consultas avançadas através de critérios dinâmicos fornecidos pelos usuários.")
                                .toString()
                        )
                        .termsOfService("")
                        .contact(new Contact().name("Júlio Mesquita").email("juliocesarmcamilo@gmail.com").url("https://github.com/devjuliomesquita"))
                        .license(new License().name("Júlio Mesquita - Licensa MIT").url("https://github.com/devjuliomesquita/AdvancedSearch_Jpa_Specification"))
                )
                .servers(new ArrayList<>(List.of(
                                new Server().description("Ambiente LOCAL").url("http://localhost:8080")
                        ))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("JPA Specification | Criteria API")
                        .url("https://docs.spring.io/spring-data/jpa/reference/jpa/specifications.html")
                );

    }
}
