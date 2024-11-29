package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;


import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotNull
    @Size(max = 3, min = 3)
    private String currencyName;

    @NotNull
    private BigDecimal exchangeRate;

    @NotNull
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
