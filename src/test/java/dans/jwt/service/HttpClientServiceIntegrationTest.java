package dans.jwt.service;

import dans.jwt.config.AsyncConfiguration;
import dans.jwt.config.AsyncConfigurationProperties;
import dans.jwt.config.HttpClientConfiguration;
import dans.jwt.config.HttpClientConfigurationProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        AsyncConfiguration.class,
        HttpClientConfiguration.class,
        HttpClientServiceImpl.class
})
@EnableConfigurationProperties({
        AsyncConfigurationProperties.class,
        HttpClientConfigurationProperties.class
})
@TestPropertySource("classpath:application.properties")
public class HttpClientServiceIntegrationTest {

    @Autowired
    private HttpClientService httpClientService;

//    @Test
    public void testGetPositions() throws Exception {
        final CompletableFuture<String> positionsFuture = httpClientService.getPositions();
        final String positions = positionsFuture.get();
        System.out.println(positions);
    }

//    @Test
    public void testGetPositionsById() throws Exception {
        final String positionsId = "32bf67e5-4971-47ce-985c-44b6b3860cdb";
        final CompletableFuture<String> positionsFuture = httpClientService.getPositions(positionsId);
        final String positions = positionsFuture.get();
        System.out.println(positions);
    }
}
