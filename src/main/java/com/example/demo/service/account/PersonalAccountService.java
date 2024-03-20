package com.example.demo.service.account;

import com.example.demo.entity.PersonalAccount;
import com.example.demo.repository.PersonalAccountRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис работы с основным счетом.
 */
@Service
public class PersonalAccountService extends AccountService<PersonalAccount> {

    public PersonalAccountService(PersonalAccountRepository repository) {
        super(repository);
    }

}
