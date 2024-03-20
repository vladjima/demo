# Сервис оплаты покупок наличными/безналичными средствами в магазинах и онлайн

## Описание

Списание средств происходит в самом начале транзакции оплаты.
Зачисление или списание бонусов построено на статусной модели с представленными шагами:

* InStoreState - производится начисление 10% бонусов от суммы покупки или 30%, если сумма покупки превышает 300 рублей
* InOnlineState - производится начисление 17% бонусов от суммы покупки или 30%, если сумма покупки превышает 300 рублей
(не производится начисление, если сумма покупки не превышает 20 рублей)
* InRefundState - производится списание 10% бонусов от суммы покупки, если она не превышает 20 рублей
* InBankState - финальный шаг, на котором транзакция завершается

## REST API

### Оплата покупки

GET /api/payment/{shopType}/{amount}
* shopType - тип магазина (SHOP, ONLINE)
* amount - сумма оплаты

Положительный HTTP код ответа 200 без тела ответа.

### Количество бонус на счете банка

GET /api/bankAccountOfEMoney

Положительный HTTP код ответа 200. В теле ответа количество бонусов.

### Количество наличных/безналичных денег

GET /api/money

Положительный HTTP код ответа 200. В теле ответа количество денег.
