package dans.jwt.controller;

import dans.jwt.entity.AuthRequest;
import dans.jwt.jwt.JwtUtil;
import dans.jwt.service.HttpClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class IndexController {

    private static final String NO_DATA = "{\"data\":\"empty\"}";

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final HttpClientService httpClientService;

    @Autowired
    public IndexController(JwtUtil jwtUtil,
                           AuthenticationManager authenticationManager,
                           HttpClientService httpClientService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.httpClientService = httpClientService;
    }

    @GetMapping("/")
    public String index() {
        return "Dans JWT Test..";
    }

    @GetMapping("/positions")
    public String positions() {
        try {
            CompletableFuture<String> positions = httpClientService.getPositions();
            return positions.get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return NO_DATA;
    }

    @GetMapping("/positions/{id}")
    public String positionsById(@PathVariable String id) {
        try {
            CompletableFuture<String> positions = httpClientService.getPositions(id);
            return positions.get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return NO_DATA;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(authRequest.getUserName(),
                                    authRequest.getPassword())
                    );
        } catch (Exception ex) {
            throw new Exception("username or password not valid");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
