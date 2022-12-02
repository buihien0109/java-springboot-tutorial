package com.example.mutilpledatabasemongo.configdb;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.example.mutilpledatabasemongo.domain.product"},
        mongoTemplateRef = ProductDbConfig.MONGO_TEMPLATE
)
public class ProductDbConfig {
    protected static final String MONGO_TEMPLATE = "productMongoTemplate";
}
