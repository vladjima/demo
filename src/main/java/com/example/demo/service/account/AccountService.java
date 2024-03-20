package com.example.demo.service.account;

import com.example.demo.entity.Account;
import com.example.demo.exception.AccountNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/**
 * Абстрактный сервис работы со счетами.
 */
@Transactional
@RequiredArgsConstructor
public abstract class AccountService<T extends Account> {

    private final JpaRepository<T, Long> repository;

    /**
     * Получение остатка счета.
     */
    public BigDecimal getBalance() {
        T account = get();
        return account.getBalance();
    }

    /**
     * Изменение остатка счета.
     *
     * @param amount Сумма операции.
     */
    public void modifyBalance(BigDecimal amount) {
        T account = get();
        BigDecimal balance = account.getBalance();
        account.setBalance(balance.add(amount));
        repository.save(account);
    }

    private T get() {
        return repository.findAll()
                .stream().findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Счет клиента не найден"));
    }

}
