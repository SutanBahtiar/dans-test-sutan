package dans.jwt;

import dans.jwt.config.AsyncConfigurationProperties;
import dans.jwt.config.HttpClientConfigurationProperties;
import dans.jwt.entity.User;
import dans.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties({
        HttpClientConfigurationProperties.class,
        AsyncConfigurationProperties.class
})
public class DansJwtTestApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DansJwtTestApplication.class, args);
    }

    @PostConstruct
    public void initUsers() {
        userRepository.save(new User("username", "password"));
    }

}
