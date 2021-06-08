package com.thedariusz.warnme;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WarnMeApplicationIT {

    @Autowired
    private ApplicationContext context;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Test
    void shouldCreateWarnMeApplicationBean() {
        assertThat(context.containsBeanDefinition("warnMeApplication")).isTrue();
    }

}