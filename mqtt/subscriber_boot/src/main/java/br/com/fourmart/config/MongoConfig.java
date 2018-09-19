package br.com.fourmart.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    private final String mongoDbUri;

    @Autowired
    public MongoConfig(@Value("${spring.data.mongodb.uri}") String mongoDbUri) {
        this.mongoDbUri = mongoDbUri;
    }

    @Bean(destroyMethod = "close")
    public MongoClient mongo() {
        return new MongoClient(new MongoClientURI(mongoDbUri));
    }
}
