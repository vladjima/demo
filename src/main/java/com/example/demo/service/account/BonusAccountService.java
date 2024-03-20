package com.example.demo.service.account;

import com.example.demo.entity.BonusAccount;
import com.example.demo.repository.BonusAccountRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис работы с бонусным счетом.
 */
@Service
public class BonusAccountService extends AccountService<BonusAccount> {

    public BonusAccountService(BonusAccountRepository repository) {
        super(repository);
    }

}
