package org.miniprojecttwo.jaruratcare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WhatsAppService {
    public String getResponse(String msg) {
        if (msg == null || msg.isBlank()) {
            return "Please provide a valid message.";
        }
        String message;
        if (msg.equalsIgnoreCase("hi")) {
            message = "Hello";

        } else if (msg.equalsIgnoreCase("bye")) {
            message = "Goodbye";

        } else {
            message = "You said: " + msg + " enter either 'hi' or 'bye'";
        }
        return message;
    }
}
