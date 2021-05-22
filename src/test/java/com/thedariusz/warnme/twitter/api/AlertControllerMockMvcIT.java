package com.thedariusz.warnme.twitter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static com.thedariusz.warnme.twitter.api.IntegrationTestBase.TweetDtoTest.AuthorDtoTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AlertController.class)
class AlertControllerMockMvcIT extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllAlerts() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get(ALERTS_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        final String jsonResponse = mvcResult.getResponse().getContentAsString();
        final List<TweetDtoTest> actualTweetList = Arrays.asList(objectMapper.readValue(jsonResponse, TweetDtoTest[].class));

        Assertions.assertThat(actualTweetList)
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
                new AuthorDtoTest("1", "imgw", "imgw ipb"),
                "2021-05-06",
                List.of("url1", "url2"),
                List.of("burze")
        );
    }

    private TweetDtoTest expectedOtherAlert() {
        return new TweetDtoTest(
                "2",
                "test",
                new AuthorDtoTest("1", "imgw", "imgw ipb"),
                "2021-05-06",
                List.of("photo1", "photo2"),
                List.of("burze")
        );
    }



}