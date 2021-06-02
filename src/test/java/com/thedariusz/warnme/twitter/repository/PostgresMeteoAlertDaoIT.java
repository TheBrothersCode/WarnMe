package com.thedariusz.warnme.twitter.repository;

import com.thedariusz.warnme.twitter.MeteoAlert;
import com.thedariusz.warnme.twitter.TwitterClientConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@Import(TwitterClientConfiguration.class)
class PostgresMeteoAlertDaoIT {

    @Autowired
    MeteoAlertSpringDao dao;

    @Test
    void save() {
        dao.save(new MeteoAlert(1, Set.of("burze"), "20210601", "test test", "123", null));
        List<MeteoAlert> meteoAlerts = dao.findAll();
        MeteoAlert testAlert = meteoAlerts.get(0);
        assertThat(testAlert)
                .isNotNull();
    }

}