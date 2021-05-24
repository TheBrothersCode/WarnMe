package com.thedariusz.warnme.api;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeteoAlertControllerRestTemplateIT extends IntegrationTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Disabled
    void shouldReturnAllAlerts() {
        //given
        final String url = "http://localhost:" + port + ALERTS_PATH;

        //when
        final ResponseEntity<TweetDtoTest[]> response = restTemplate.getForEntity(url, TweetDtoTest[].class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(
                        expectedMeteoAlert(),
                        expectedOtherAlert()
                );
    }

    private TweetDtoTest expectedMeteoAlert() {
        return new TweetDtoTest(
                "1",
                "test",
                new TweetDtoTest.AuthorDtoTest("1", "imgw", "imgw ipb"),
                "2021-05-06",
                List.of("url1", "url2"),
                List.of("burze")
        );
    }

    private TweetDtoTest expectedOtherAlert() {
        return new TweetDtoTest(
                "2",
                "test",
                new TweetDtoTest.AuthorDtoTest("1", "imgw", "imgw ipb"),
                "2021-05-06",
                List.of("photo1", "photo2"),
                List.of("burze")
        );
    }

}