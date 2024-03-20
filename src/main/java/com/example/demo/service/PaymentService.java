package com.example.demo.service;

import com.example.demo.model.ShopType;

import java.math.BigDecimal;

/**
 * Сервис оплаты.
 */
public interface PaymentService {

    /**
     * Метод выполняет оплату наличными/безналичными средствами в магазинах и онлайн.
     */
    void purchase(ShopType shopType, BigDecimal amount);

}
