package com.juliomesquita.application.infra.utils;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ApiResponses(
        Object data,
        String message,
        HttpStatus responseCode
) {
}
