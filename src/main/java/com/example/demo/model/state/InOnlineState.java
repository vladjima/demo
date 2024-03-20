package com.example.demo.model.state;

import com.example.demo.entity.BonusAccount;
import com.example.demo.model.State;
import com.example.demo.model.Transaction;
import com.example.demo.service.account.AccountService;

import java.math.BigDecimal;

/**
 * Шаг, на котором производится начисление 17% бонусов от суммы покупки
 * или 30%, если сумма покупки превышает 300 рублей.
 * Не производится начисление, если сумма покупки не превышает 20 рублей.
 */
public class InOnlineState extends State {

    public InOnlineState(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void setBonus(AccountService<BonusAccount> service) {
        BigDecimal amount = transaction.getAmount();
        if (isRefundAmount()) {
            return;
        }
        BigDecimal bonusAmount;
        if (amount.compareTo(new BigDecimal(300)) > 0) {
            bonusAmount = amount.multiply(new BigDecimal("0.3"));
        } else {
            bonusAmount = amount.multiply(new BigDecimal("0.17"));
        }
        service.modifyBalance(bonusAmount);
    }

    @Override
    public void next() {
        if (isRefundAmount()) {
            transaction.setState(new InRefundState(transaction));
        } else {
            transaction.setState(new InBankState(transaction));
        }
    }

    private boolean isRefundAmount() {
        BigDecimal amount = transaction.getAmount();
        return amount.compareTo(new BigDecimal(20)) < 0;
    }

}
