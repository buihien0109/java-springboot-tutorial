package com.example.mutilpledatabasemongo.configdb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleMongoConfig {
    @Primary
    @Bean(name = "userProperties")
    @ConfigurationProperties(prefix = "mongodb.user")
    public MongoProperties getUserProps() throws Exception {
        return new MongoProperties();
    }

    @Bean(name = "postProperties")
    @ConfigurationProperties(prefix = "mongodb.post")
    public MongoProperties getPostProps() throws Exception {
        return new MongoProperties();
    }

    @Bean(name = "productProperties")
    @ConfigurationProperties(prefix = "mongodb.product")
    public MongoProperties getProductProps() throws Exception {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "userMongoTemplate")
    public MongoTemplate userMongoTemplate() throws Exception {
        return new MongoTemplate(userMongoDatabaseFactory(getUserProps()));
    }

    @Bean(name = "postMongoTemplate")
    public MongoTemplate postMongoTemplate() throws Exception {
        return new MongoTemplate(postMongoDatabaseFactory(getPostProps()));
    }

    @Bean(name = "productMongoTemplate")
    public MongoTemplate productMongoTemplate() throws Exception {
        return new MongoTemplate(productMongoDatabaseFactory(getProductProps()));
    }

    @Primary
    @Bean
    public MongoDatabaseFactory userMongoDatabaseFactory(MongoProperties mongo) throws Exception {
        return new SimpleMongoClientDatabaseFactory(
                mongo.getUri()
        );
    }

    @Bean
    public MongoDatabaseFactory postMongoDatabaseFactory(MongoProperties mongo) throws Exception {
        return new SimpleMongoClientDatabaseFactory(
                mongo.getUri()
        );
    }

    @Bean
    public MongoDatabaseFactory productMongoDatabaseFactory(MongoProperties mongo) throws Exception {
        return new SimpleMongoClientDatabaseFactory(
                mongo.getUri()
        );
    }
}