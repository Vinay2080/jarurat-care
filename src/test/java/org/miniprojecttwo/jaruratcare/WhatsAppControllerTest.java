package org.miniprojecttwo.jaruratcare;

import org.junit.jupiter.api.Test;
import org.miniprojecttwo.jaruratcare.service.WhatsAppService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WhatsAppControllerTest {

    @Test
    void sendMessageReturnsResponseForValidRequest() throws Exception {
        WhatsAppService whatsAppService = mock(WhatsAppService.class);
        MockMvc mockMvc = buildMockMvc(whatsAppService);

        when(whatsAppService.getResponse("Hi")).thenReturn("Hello");

        mockMvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"msg\":\"Hi\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Hello"));
    }

    @Test
    void sendMessageReturnsBadRequestForBlankMessage() throws Exception {
        WhatsAppService whatsAppService = mock(WhatsAppService.class);
        MockMvc mockMvc = buildMockMvc(whatsAppService);

        mockMvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"msg\":\"   \"}"))
                .andExpect(status().isBadRequest());

        verify(whatsAppService, never()).getResponse(anyString());
    }

    private MockMvc buildMockMvc(WhatsAppService whatsAppService) {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();

        return MockMvcBuilders
                .standaloneSetup(new WhatsAppController(whatsAppService))
                .setValidator(validator)
                .build();
    }
}


