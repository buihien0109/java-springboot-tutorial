package com.example.mutilpledatabasemongo;

import com.example.mutilpledatabasemongo.domain.post.Post;
import com.example.mutilpledatabasemongo.domain.post.PostRepository;
import com.example.mutilpledatabasemongo.domain.product.Product;
import com.example.mutilpledatabasemongo.domain.product.ProductRepository;
import com.example.mutilpledatabasemongo.domain.user.User;
import com.example.mutilpledatabasemongo.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.util.List;

@Slf4j
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class MutilpleDatabaseMongoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(MutilpleDatabaseMongoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("************************************************************");
        log.info("Start printing mongo objects");
        log.info("************************************************************");

        // Luu lai thong tin
        userRepository.save(new User(null, "Bui Hien"));
        userRepository.save(new User(null, "Thu Hang"));

        postRepository.save(new Post(null, "Xac thuc REST API"));
        postRepository.save(new Post(null, "Giai thich ve HTTP"));

        productRepository.save(new Product(null, "Ao dai tay"));
        productRepository.save(new Product(null, "Quan au ai"));

        // In ra thong tin
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);

        List<Post> posts = postRepository.findAll();
        posts.forEach(System.out::println);

        List<Product> products = productRepository.findAll();
        products.forEach(System.out::println);

        log.info("************************************************************");
        log.info("Ended printing mongo objects");
        log.info("************************************************************");
    }
}
