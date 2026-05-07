package chatapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        final String authorization = "Authorization";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(authorization))
                .components(new Components()
                                .addSecuritySchemes(authorization, new SecurityScheme()
                                        .name(authorization)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                                )
                );
    }

}
