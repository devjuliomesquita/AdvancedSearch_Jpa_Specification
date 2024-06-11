package com.juliomesquita.application.infra.utils;

import com.juliomesquita.application.domain.enums.SortType;
import lombok.Builder;

@Builder
public record PageRequestDto(
        Integer page,
        Integer perPage,
        SortType sort
) {
}
