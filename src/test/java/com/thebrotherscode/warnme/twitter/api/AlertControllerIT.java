package com.thebrotherscode.warnme.twitter.api;

import com.thebrotherscode.warnme.WarnMeApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlertControllerIT {
    //    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void shouldDoTest() {
//        mockMvc.perform(get("/alerts")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].name", is("bob")));
//    }
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        String result = this.restTemplate.getForObject("http://localhost:" + port + "/alerts", String.class);
        assertThat(result).isEqualTo("[{\"alertId\":\"1\",\"text\":\"test\",\"author\":{\"id\":\"1\",\"name\":\"imgw\",\"username\":\"imgw ipb\"},\"creationDate\":\"2021-05-06\",\"tweetType\":\"METEO\",\"mediaList\":[\"url1\",\"url2\"],\"hashTags\":[\"burze\"]},{\"alertId\":\"2\",\"text\":\"test\",\"author\":{\"id\":\"1\",\"name\":\"imgw\",\"username\":\"imgw ipb\"},\"creationDate\":\"2021-05-06\",\"tweetType\":\"OTHER\",\"mediaList\":[\"photo1\",\"photo2\"],\"hashTags\":[\"burze\"]}]");
    }

//    public static class TweetTestDto {
//        public String id;
//        public String text;
//
//        public TweetTestDto(String id, String text) {
//            this.id = id;
//            this.text = text;
//        }
//    }
}