package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.service.UserService;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeResponseDto {

    private Long exchangeId;

    private BigDecimal amountInKrw;

    private BigDecimal exchangeRate;

    private BigDecimal amountAfterExchange;

    private String status;

    public ExchangeResponseDto(Long exchangeId, BigDecimal amountInKrw, BigDecimal exchangeRate, BigDecimal amountAfterExchange, String status) {
        this.exchangeId = exchangeId;
        this.amountInKrw = amountInKrw;
        this.exchangeRate = exchangeRate;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }
    
    public static ExchangeResponseDto toDto (UserCurrency userCurrency) {

        return new ExchangeResponseDto(
                userCurrency.getId(),
                userCurrency.getAmountInKrw(),
                userCurrency.getCurrency().getExchangeRate(),
                userCurrency.getAmountAfterExchange(),
                userCurrency.getStatus()
        );
    }
}
