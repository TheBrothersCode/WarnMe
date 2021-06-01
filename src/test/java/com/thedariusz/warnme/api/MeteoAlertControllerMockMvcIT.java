package com.thedariusz.warnme.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.thedariusz.warnme.MeteoAlertDao;
import com.thedariusz.warnme.MeteoAlertOrigin;
import com.thedariusz.warnme.twitter.MeteoAlert;
import com.thedariusz.warnme.twitter.TwitterClientConfiguration;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.mockserver.model.MediaType;
import org.mockserver.model.RequestDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MeteoAlertController.class)
@Import(TwitterClientConfiguration.class)
class MeteoAlertControllerMockMvcIT extends IntegrationTestBase {

    static ClientAndServer mockServer;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MeteoAlertDao meteoAlertDao;

    @BeforeEach
    public void init() {
        meteoAlertDao.deleteAll();
        mockServer.reset();
    }

    @BeforeAll
    public static void setup() {
        mockServer = ClientAndServer.startClientAndServer(8092);
    }

    @Test
    void fetchAllShouldSaveSingleAlertInMemory() throws Exception {
        //given
        String userId = "1";
        mockTwitterResponse(userId);
        
        mockMvc.perform(post(ALERTS_PATH + "/1"))
                .andDo(print())
                .andExpect(status().isOk());


        final List<MeteoAlert> meteoAlerts = meteoAlertDao.fetchAll();
        assertThat(meteoAlerts)
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparator(RecursiveComparisonConfiguration.builder()
                        .withIgnoredFields("creationDate", "description", "media", "meteoAlertOrigin")
                        .build())
                .contains(
                        meteoAlert(0, Set.of("burze"))
//                        meteoAlert(1, Set.of("burze", "burza", "deszcz", "grad"))
                );

    }

    private void mockTwitterResponse(String userId) {
        mockServer.when(fetchAllTweetsRequest(userId))
                .respond(this::allTweetsResponse);
    }

    private HttpResponse allTweetsResponse(HttpRequest request) throws IOException {
        String jsonResponse = Files.readString(Path.of("src/test/resources/twitter_response.json"));
        return HttpResponse.response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON)
                .withBody(JsonBody.json(jsonResponse));
    }

    private RequestDefinition fetchAllTweetsRequest(String userId) {
        return HttpRequest.request()
                .withMethod("GET")
                .withPath("/2/users/" + userId + "/tweets")
                .withContentType(MediaType.APPLICATION_JSON)
                .withHeader(HttpHeaders.AUTHORIZATION, "Bearer abc");
    }

    private MeteoAlert meteoAlert(int level, Set<String> categories) {
        final MeteoAlertOrigin meteoAlertOrigin = new MeteoAlertOrigin("Twitter", "1139834822011084801", "1");
        return new MeteoAlert(level, categories, null, null, meteoAlertOrigin, null);
    }

}