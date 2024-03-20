package com.example.demo.service;

import com.example.demo.model.ShopType;
import com.example.demo.entity.BonusAccount;
import com.example.demo.entity.PersonalAccount;
import com.example.demo.model.State;
import com.example.demo.service.account.AccountService;
import com.example.demo.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final AccountService<PersonalAccount> personalAccountService;
    private final AccountService<BonusAccount> bonusAccountService;

    @Override
    public void purchase(ShopType shopType, BigDecimal amount) {
        Transaction transaction = new Transaction(shopType, amount);
        personalAccountService.modifyBalance(amount.negate());
        while (!transaction.isFinished()) {
            State state = transaction.getState();
            state.setBonus(bonusAccountService);
            state.next();
            log.debug(bonusAccountService.getBalance().toString());
        }
    }

}
