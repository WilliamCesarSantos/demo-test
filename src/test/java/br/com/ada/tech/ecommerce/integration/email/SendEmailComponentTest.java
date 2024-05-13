package br.com.ada.tech.ecommerce.integration.email;

import br.com.ada.tech.config.GreenEmailExtension;
import br.com.ada.tech.config.GreenMailConfig;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootTest
@Import(GreenMailConfig.class)
@ExtendWith(GreenEmailExtension.class)
public class SendEmailComponentTest {

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private GreenMail greenMail;

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
