package com.nhtr.gatewayservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

import com.nhtr.commonmodule.constant.AuthConst;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import okhttp3.mockwebserver.MockWebServer;

import java.net.URI;
import java.util.Arrays;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class GatewayIntegrationTest {
    private static final String BASE_GATEWAY_URL_PATH = "/gateway";
    private static final String ACCOUNT_SVC_ENDPOINT_URL = "/account-service/api/users";

    @Autowired
    private WebTestClient webTestClient;

    MockWebServer accountResourceServer = new MockWebServer();

    @BeforeEach
    public void setUp() throws Exception {
        accountResourceServer.start(9091);
    }

    @AfterEach
    public void tearDown() throws Exception {
        accountResourceServer.shutdown();
    }

    @Test
    public void givenJwt_whenRequestExampleEndpoint_thenRequestForwardedToAccountServer() throws Exception {
        String mockedAccountResources = "[{\"id\":1,\"username\":\"Foo\"}, {\"id\":2,\"username\":\"Bar\"}]";

        accountResourceServer.enqueue(new MockResponse().setBody(mockedAccountResources)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        this.webTestClient.mutateWith(mockJwt().jwt(jwt -> jwt.claim("scope", "read write custom")
                .subject("customSubjectId")))
                .get()
                .uri(BASE_GATEWAY_URL_PATH + "/account/users")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(response -> {
                    String bodyAsString = new String(response.getResponseBodyContent());
                    assertThat(bodyAsString).contains("Foo")
                            .contains("Bar");
                });

        RecordedRequest capturedProjectRequest = accountResourceServer.takeRequest();
        assertThat(capturedProjectRequest.getMethod()).isEqualTo(HttpMethod.GET.name());
        String accountPath = new URI(ACCOUNT_SVC_ENDPOINT_URL).getPath();
        assertThat(capturedProjectRequest.getPath()).isEqualTo(accountPath);
        assertThat(capturedProjectRequest.getHeaders()
                .toMultimap()).hasEntrySatisfying(AuthConst.AUTH_HEADER, valueList -> valueList.containsAll(Arrays.asList("SCOPE_write", "SCOPE_read", "SCOPE_custom")));
        assertThat(capturedProjectRequest.getHeader(AuthConst.USER_ID_HEADER)).isEqualTo("customSubjectId");
    }

    @Test
    public void givenJwt_whenRequestUnmappedEndpoint_then404NotFound() throws Exception {
        this.webTestClient.mutateWith(mockJwt().jwt(jwt -> jwt.claim("scope", "read write custom")
                .subject("customSubjectId")))
                .get()
                .uri(BASE_GATEWAY_URL_PATH + "/other")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void givenNoJwt_whenHttpGet_thenUnauthorized() throws Exception {
        this.webTestClient.get()
                .uri(BASE_GATEWAY_URL_PATH + "/account/users")
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }
}
