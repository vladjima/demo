package com.example.demo.model.state;

import com.example.demo.entity.BonusAccount;
import com.example.demo.model.State;
import com.example.demo.model.Transaction;
import com.example.demo.service.account.AccountService;

import java.math.BigDecimal;

/**
 * Шаг, на котором производится начисление 10% бонусов от суммы покупки
 * или 30%, если сумма покупки превышает 300 рублей.
 */
public class InStoreState extends State {

    public InStoreState(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void setBonus(AccountService<BonusAccount> service) {
        BigDecimal amount = transaction.getAmount();
        BigDecimal bonusAmount;
        if (amount.compareTo(new BigDecimal(300)) > 0) {
            bonusAmount = amount.multiply(new BigDecimal("0.3"));
        } else {
            bonusAmount = amount.multiply(new BigDecimal("0.1"));
        }
        service.modifyBalance(bonusAmount);
    }

    @Override
    public void next() {
        transaction.setState(new InBankState(transaction));
    }
}
