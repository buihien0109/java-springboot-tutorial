package com.example.mutilpledatabasemongo.configdb;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.example.mutilpledatabasemongo.domain.user"},
        mongoTemplateRef = UserDbConfig.MONGO_TEMPLATE
)
public class UserDbConfig {
    protected static final String MONGO_TEMPLATE = "userMongoTemplate";
}
