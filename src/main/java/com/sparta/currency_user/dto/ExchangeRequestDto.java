package com.sparta.currency_user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long currencyId;

    @NotNull
    private BigDecimal amountInKrw;

}
