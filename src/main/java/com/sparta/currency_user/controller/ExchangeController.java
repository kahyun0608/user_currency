package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.service.CurrencyService;
import com.sparta.currency_user.service.ExchangeService;
import com.sparta.currency_user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;
    private final UserService userService;
    private final CurrencyService currencyService;

    @PostMapping()
    public ResponseEntity<ExchangeResponseDto> exchangeCurrency (@Valid @RequestBody ExchangeRequestDto requestDto){

        User user = userService.findUserById(requestDto.getUserId());
        Currency currency = currencyService.findCurrencyById(requestDto.getCurrencyId());
        BigDecimal amountInKrw = requestDto.getAmountInKrw();
        BigDecimal amountAfterExchange = new BigDecimal(String.valueOf(amountInKrw.divide(currency.getExchangeRate(),2, RoundingMode.HALF_EVEN)));

        UserCurrency exchangeRequest = new UserCurrency(user, currency, amountInKrw, amountAfterExchange, "normal");

        return ResponseEntity.ok().body(exchangeService.save(exchangeRequest));
    }




}
