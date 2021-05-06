package com.thebrotherscode.warnme;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WarnMeApplicationTest {

    WarnMeApplication app = new WarnMeApplication();

    @Test
    void shouldSayHelloToAdam() {
        //given
        final String adam = "Adam";

        //when
        final String actual = app.sayHello(adam);

        //then
        final String expected = "Hi Adam";
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}