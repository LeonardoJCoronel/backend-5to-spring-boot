package proyecto.proyecto.demo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("proyecto-public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Proyecto API")
                        .description("API para gestionar diferentes entidades en un proyecto.")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .url("url_de_tu_web")
                                .email("tu_correo@dominio.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Proyecto API Documentation")
                        .url("http://localhost:8080"));
    }
}
