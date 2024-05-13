package br.com.ada.tech.config;

import com.icegreen.greenmail.util.GreenMail;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class GreenEmailExtension implements BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        var appContext = SpringExtension.getApplicationContext(context);
        var greenMail = appContext.getBean(GreenMail.class);

        greenMail.setUser("green@mail.com", "123456");
        greenMail.start();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        var appContext = SpringExtension.getApplicationContext(context);
        var greenMail = appContext.getBean(GreenMail.class);

        greenMail.stop();
    }

}
