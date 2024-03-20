package com.example.demo.model;

import com.example.demo.entity.BonusAccount;
import com.example.demo.service.account.AccountService;

/**
 * Шаг флоу, по которым перемещается оплата в рамках одной транзакции.
 *
 * @see Transaction
 */
public abstract class State {

    protected final Transaction transaction;

    protected State(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Метод зачисляет сумму на бонусный счет и переходит на следующий шаг флоу.
     *
     * @param service Сервис, работающий с бонусным счетом.
     */
    public abstract void setBonus(AccountService<BonusAccount> service);

    /**
     * Переход на следующий шаг.
     */
    public abstract void next();

}
