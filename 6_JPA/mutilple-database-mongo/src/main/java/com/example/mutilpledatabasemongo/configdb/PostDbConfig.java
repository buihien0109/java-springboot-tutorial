package com.example.mutilpledatabasemongo.configdb;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.example.mutilpledatabasemongo.domain.post"},
        mongoTemplateRef = PostDbConfig.MONGO_TEMPLATE
)
public class PostDbConfig {
    protected static final String MONGO_TEMPLATE = "postMongoTemplate";
}
