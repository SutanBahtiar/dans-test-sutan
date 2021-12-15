package dans.jwt.service;

import java.util.concurrent.CompletableFuture;

public interface HttpClientService {

    CompletableFuture<String> getPositions() throws Exception;

    CompletableFuture<String> getPositions(String positionsId) throws Exception;
}
