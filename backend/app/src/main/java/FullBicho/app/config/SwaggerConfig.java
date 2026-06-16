package FullBicho.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI fullBichoOpenAPI() {
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Servidor local");

        Contact contact = new Contact()
                .name("FullBicho")
                .email("2023008264@ifam.edu.br");

        Info info = new Info()
                .title("FullBicho API")
                .version("1.0.0")
                .description("Documentação da API FullBicho | Este projeto é apenas para fins educacionais e didáticos, não incentivando práticas de jogos de azar.\r\n")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}