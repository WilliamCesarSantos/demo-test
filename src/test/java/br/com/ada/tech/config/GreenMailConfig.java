package br.com.ada.tech.config;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.inject.Singleton;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class GreenMailConfig {

    @Bean
    @Singleton
    public GreenMail produces() {
        return new GreenMail(
                new ServerSetup(3025, "localhost", "smtp")
        );
    }

}
