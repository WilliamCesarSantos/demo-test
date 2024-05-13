package br.com.ada.tech.ecommerce.integration.email;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SendEmailComponentTest {

    @Autowired
    private SendEmail sendEmail;

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(
            ServerSetup.SMTP.port(3025)
    ).withConfiguration(GreenMailConfiguration.aConfig()
            .withUser("green@mail.com", "123456")
    ).withPerMethodLifecycle(false);

    private String from = "unit@test.com";
    private List<String> to = List.of("test@unit.com");
    private String subject = "unit-test";
    private String content = "Testing with green mail";

    @Test
    public void send_whenEmailIsValid_shouldSendIt() throws MessagingException {
        sendEmail.send(from, to, subject, content);

        var messages = greenMail.getReceivedMessages();
        Assertions.assertEquals(1, messages.length);
    }

}
