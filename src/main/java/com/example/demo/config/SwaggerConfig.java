package com.example.demo.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement; // ✅ Annotation
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.*; // ✅ Model class
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition(

		security = @SecurityRequirement(name = "bearerAuth") // annotation dùng ở đây
)
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("bearerAuth",
								new SecurityScheme()
										.type(SecurityScheme.Type.HTTP)
										.scheme("bearer")
										.bearerFormat("JWT")
										.in(SecurityScheme.In.HEADER)
						)
				)
				.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearerAuth")) // dùng model class ở đây
				.info(new Info()
						.title("SGU Enterprise Information System API Documentation")
						.version("1.0")
						.description("API Documentation for SGU-EIS Project")
				);
	}
}
