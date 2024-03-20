package com.example.demo.controller;

import com.example.demo.model.ShopType;
import com.example.demo.entity.BonusAccount;
import com.example.demo.entity.PersonalAccount;
import com.example.demo.service.PaymentService;
import com.example.demo.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DemoController {

    private final PaymentService paymentService;
    private final AccountService<PersonalAccount> personalAccountService;
    private final AccountService<BonusAccount> bonusAccountService;

    /**
     * Оплата покупки.
     */
    @GetMapping("/payment/{shopType}/{amount}")
    public void purchase(@PathVariable ShopType shopType, @PathVariable BigDecimal amount) {
        paymentService.purchase(shopType, amount);
    }

    /**
     * Количество бонус на счете банка.
     */
    @GetMapping("/bankAccountOfEMoney")
    public BigDecimal getBonusAmount() {
        return bonusAccountService.getBalance();
    }

    /**
     * Количество наличных/безналичных денег.
     */
    @GetMapping("/money")
    public BigDecimal purchase() {
        return personalAccountService.getBalance();
    }

}
