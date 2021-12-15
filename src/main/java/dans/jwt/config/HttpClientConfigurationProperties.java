package dans.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("http-client")
public class HttpClientConfigurationProperties {

    private int maxTotalPool;
}
