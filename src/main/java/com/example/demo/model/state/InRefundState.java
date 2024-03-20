package com.example.demo.model.state;

import com.example.demo.entity.BonusAccount;
import com.example.demo.model.State;
import com.example.demo.model.Transaction;
import com.example.demo.service.account.AccountService;

import java.math.BigDecimal;

/**
 * Шаг, на котором производится списание 10% бонусов от суммы покупки,
 * если она не превышает 20 рублей.
 */
public class InRefundState extends State {

    public InRefundState(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void setBonus(AccountService<BonusAccount> service) {
        BigDecimal amount = transaction.getAmount();
        service.modifyBalance(amount.multiply(new BigDecimal("-0.1")));
    }

    @Override
    public void next() {
        transaction.setState(new InBankState(transaction));
    }

}
