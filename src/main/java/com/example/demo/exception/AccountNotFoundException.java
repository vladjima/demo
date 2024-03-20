package com.example.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Исключение возникает, когда не найден счет клиента.
 */
@Getter
@RequiredArgsConstructor
public class AccountNotFoundException extends RuntimeException {

    private final String message;

}
