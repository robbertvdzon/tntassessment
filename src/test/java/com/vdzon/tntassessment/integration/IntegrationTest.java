package com.vdzon.tntassessment.integration;

import com.vdzon.tntassessment.Main;
import com.vdzon.tntassessment.settings.Settings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IntegrationTest {

    private final Main main = new Main(loadTestSettings());
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    private Settings loadTestSettings() {
        return Settings
                .builder()
                .port(7000)
                .build();
    }

    @BeforeEach
    void beforeEach(){
        main.start();
    }

    @AfterEach
    void afterEach(){
        main.stop();
    }

    @Test
    void healthTest() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = buildGetRequest("http://localhost:7000/health");
        HttpResponse<String> send = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(send.statusCode()).isEqualTo(200);
        assertThat(send.body()).isEqualTo("ok");
    }

    @Test
    void testHappyflow() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = buildGetRequest("http://localhost:7000/aggregation?pricing=NL,CN&track=109347263,123456891&shipments=109347263,123456891");
        HttpResponse<String> send = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(send.statusCode()).isEqualTo(200);
        assertThat(send.body()).isEqualTo("ok");
    }

    private HttpRequest buildGetRequest(String url) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
    }
}
