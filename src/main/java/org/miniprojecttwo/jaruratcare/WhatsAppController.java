package org.miniprojecttwo.jaruratcare;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.miniprojecttwo.jaruratcare.dto.MsgResponse;
import org.miniprojecttwo.jaruratcare.dto.MsgString;
import org.miniprojecttwo.jaruratcare.service.WhatsAppService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
@Slf4j

public class WhatsAppController {

    private final WhatsAppService whatsAppService;

    public WhatsAppController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @PostMapping()
    public MsgResponse sendMessage(
            @Valid
            @RequestBody
            MsgString msgString
    ) {
        log.info("Received webhook message request");

        String msg = whatsAppService.getResponse(msgString.msg());
        log.info("Responded to webhook message request");
        return new MsgResponse(msg);
    }
}
