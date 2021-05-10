package com.thebrotherscode.warnme.twitter.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/alerts")
public class AlertController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TweetDto> getAllAlerts() {
        return null;
    }

}
