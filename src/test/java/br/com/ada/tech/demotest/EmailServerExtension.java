package br.com.ada.tech.demotest;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class EmailServerExtension implements
        BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("Iniciando o serviço de e-mail");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("Parando o serviço de e-mail");
    }
}
