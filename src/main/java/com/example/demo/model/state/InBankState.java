package com.example.demo.model.state;

import com.example.demo.entity.BonusAccount;
import com.example.demo.model.State;
import com.example.demo.model.Transaction;
import com.example.demo.service.account.AccountService;

/**
 * Финальный шаг, на котором транзакция завершается.
 */
public class InBankState extends State {

    public InBankState(Transaction transaction) {
        super(transaction);
        transaction.setFinished(true);
    }

    @Override
    public void setBonus(AccountService<BonusAccount> service) {
    }

    @Override
    public void next() {
    }
}
