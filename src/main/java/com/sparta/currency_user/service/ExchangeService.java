package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    public ExchangeResponseDto save (UserCurrency userCurrency) {
        UserCurrency savedUserCurrency= exchangeRepository.save(userCurrency);
        return ExchangeResponseDto.toDto(savedUserCurrency);
    }

}
