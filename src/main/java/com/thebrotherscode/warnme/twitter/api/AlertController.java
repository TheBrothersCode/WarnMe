package com.thebrotherscode.warnme.twitter.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @GetMapping(produces = "application/json")
    public List<TweetDto> getAlert() {
        return List.of(TweetDto.fakeMeteo(), TweetDto.fakeOther());
    }

}
