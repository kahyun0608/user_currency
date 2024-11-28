package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    public ExchangeResponseDto save (UserCurrency userCurrency) {
        UserCurrency savedUserCurrency= exchangeRepository.save(userCurrency);
        return ExchangeResponseDto.toDto(savedUserCurrency);
    }

    public List<ExchangeResponseDto> findExchangeRequest (Long userId) {
        List<UserCurrency> exchangeRequest = exchangeRepository.findExchangeRequest(userId);
        return exchangeRequest.stream()
                .map(ExchangeResponseDto::toDto).collect(Collectors.toList());
    }
}
