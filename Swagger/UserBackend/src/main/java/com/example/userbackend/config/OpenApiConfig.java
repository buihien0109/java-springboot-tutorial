package com.example.userbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Management API List")
                        .description("Danh sách các chức năng của ứng dụng quản lý User")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Bùi Hiên")
                                .email("hien@techmaster.vn"))
                );
    }
}
