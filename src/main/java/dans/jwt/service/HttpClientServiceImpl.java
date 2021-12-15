package dans.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class HttpClientServiceImpl implements HttpClientService {

    private static final String URL_POSITIONS = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
    private static final String URL_POSITIONS_BY_ID = "http://dev3.dansmultipro.co.id/api/recruitment/positions/";

    private final CloseableHttpClient httpClient;

    @Autowired
    public HttpClientServiceImpl(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Async
    @Override
    public CompletableFuture<String> getPositions() throws Exception {
        final String pageContent = get();
        if (null == pageContent)
            throw new Exception("Error when request " + URL_POSITIONS);
        return CompletableFuture.completedFuture(pageContent);
    }

    @Async
    @Override
    public CompletableFuture<String> getPositions(String positionsId) throws Exception {
        final String pageContent = get(positionsId);
        if (null == pageContent)
            throw new Exception("Error when request " + URL_POSITIONS_BY_ID + positionsId);
        return CompletableFuture.completedFuture(pageContent);
    }

    public String get() {
        return get(null);
    }

    public String get(String positionsId) {
        try {
            final String url = null == positionsId
                    ? URL_POSITIONS
                    : URL_POSITIONS_BY_ID + positionsId;

            log.info("call url:{}", url);

            final ClassicHttpRequest httpRequest = ClassicRequestBuilder.get()
                    .setUri(new URI(url))
                    .build();
            return httpClient.execute(httpRequest, responseHandler());
        } catch (IOException | URISyntaxException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public HttpClientResponseHandler<String> responseHandler() {
        return response -> {
            final int status = response.getCode();
            if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                final HttpEntity entity = response.getEntity();
                try {
                    return entity != null ? EntityUtils.toString(entity) : null;
                } catch (final ParseException ex) {
                    throw new ClientProtocolException(ex);
                }
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }
}
