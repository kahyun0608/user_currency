package com.sparta.currency_user.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class EveryExchangeResponseDto {

    private final Long userId;

    private final Long count;

    private final BigDecimal totalAmountInKrw;

    public EveryExchangeResponseDto(Long userId, Long count, BigDecimal totalAmountInKrw) {
        this.userId = userId;
        this.count = count;
        this.totalAmountInKrw = totalAmountInKrw;
    }
}
