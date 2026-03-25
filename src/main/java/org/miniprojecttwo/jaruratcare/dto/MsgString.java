package org.miniprojecttwo.jaruratcare.dto;

import jakarta.validation.constraints.NotBlank;

public record MsgString(
        @NotBlank
        String msg
) {
}
