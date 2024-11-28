package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.service.CurrencyService;
import com.sparta.currency_user.service.ExchangeService;
import com.sparta.currency_user.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;
    private final UserService userService;
    private final CurrencyService currencyService;

    //환전 요청 수행
    @PostMapping()
    public ResponseEntity<ExchangeResponseDto> exchangeCurrency (@Valid @RequestBody ExchangeRequestDto requestDto){

        User user = userService.findUserById(requestDto.getUserId());
        Currency currency = currencyService.findCurrencyById(requestDto.getCurrencyId());
        BigDecimal amountInKrw = requestDto.getAmountInKrw();
        BigDecimal amountAfterExchange = new BigDecimal(String.valueOf(amountInKrw.divide(currency.getExchangeRate(),2, RoundingMode.HALF_EVEN)));

        UserCurrency exchangeRequest = new UserCurrency(user, currency, amountInKrw, amountAfterExchange, "normal");

        return ResponseEntity.ok().body(exchangeService.save(exchangeRequest));
    }

    //환전 요청 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<ExchangeResponseDto>> findExchangeRequest (@PathVariable("userId") Long userId) {

        return ResponseEntity.ok().body(exchangeService.findExchangeRequest(userId));
    }

    //환전 요청 상태 취소
    @Transactional
    @PatchMapping("/{exchangeId}")
    public ResponseEntity<ExchangeResponseDto> cancelExchange (@PathVariable("exchangeId") Long exchangeId) {
        return ResponseEntity.ok().body(exchangeService.cancelExchange(exchangeId));
    }

}
