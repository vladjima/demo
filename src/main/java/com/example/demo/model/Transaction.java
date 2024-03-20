package com.example.demo.model;

import com.example.demo.model.state.InOnlineState;
import com.example.demo.model.state.InStoreState;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Транзакция оплаты с переходом на разные шаги флоу.
 *
 * @see State
 */
@Getter
@Setter
public class Transaction {

    private final ShopType shopType;
    private final BigDecimal amount;

    private State state;
    private boolean isFinished = false;

    public Transaction(ShopType shopType, BigDecimal amount) {
        this.shopType = shopType;
        this.amount = amount;
        if (shopType == ShopType.SHOP) {
            this.state = new InStoreState(this);
        } else {
            this.state = new InOnlineState(this);
        }
    }


}
