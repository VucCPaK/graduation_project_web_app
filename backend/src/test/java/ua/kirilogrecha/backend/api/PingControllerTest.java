package ua.kirilogrecha.backend.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PingControllerTest {
    @Value("${server.address}")
    private String serverAddress;

    @Value("${server.port}")
    private String serverPort;

    @Test
    void ping() {
        assertEquals("pong", WebClient
                                        .create(serverAddress + ":" + serverPort + "/api/ping")
                                        .get()
                                        .retrieve()
                                        .bodyToMono(String.class)
                                        .block());
    }
}