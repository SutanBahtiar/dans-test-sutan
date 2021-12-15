package dans.jwt.config;

import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfiguration {

    private final HttpClientConfigurationProperties httpClientConfigurationProperties;

    @Autowired
    public HttpClientConfiguration(HttpClientConfigurationProperties httpClientConfigurationProperties) {
        this.httpClientConfigurationProperties = httpClientConfigurationProperties;
    }

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        final PoolingHttpClientConnectionManager poolingHttpClientConnectionManager
                = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(httpClientConfigurationProperties.getMaxTotalPool());
        return poolingHttpClientConnectionManager;
    }

    @Bean
    public CloseableHttpClient httpClient() {
        final BasicCookieStore cookieStore = new BasicCookieStore();

        return HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .setConnectionManager(poolingHttpClientConnectionManager())
                .build();
    }
}
