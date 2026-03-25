package org.miniprojecttwo.jaruratcare.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WhatsAppServiceTest {

    private final WhatsAppService whatsAppService = new WhatsAppService();

    @Test
    void getResponseReturnsPromptForNullMessage() {
        assertEquals("Please provide a valid message.", whatsAppService.getResponse(null));
    }

    @Test
    void getResponseReturnsPromptForBlankMessage() {
        assertEquals("Please provide a valid message.", whatsAppService.getResponse("   "));
    }

    @Test
    void getResponseReturnsHelloForHi() {
        assertEquals("Hello", whatsAppService.getResponse("Hi"));
    }

    @Test
    void getResponseReturnsGoodbyeForBye() {
        assertEquals("Goodbye", whatsAppService.getResponse("bye"));
    }

    @Test
    void getResponseReturnsFallbackForUnknownMessage() {
        assertEquals(
                "You said: help enter either 'hi' or 'bye'",
                whatsAppService.getResponse("help")
        );
    }
}

