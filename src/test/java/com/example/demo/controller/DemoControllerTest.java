package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.model.ShopType;
import com.example.demo.entity.BonusAccount;
import com.example.demo.entity.PersonalAccount;
import com.example.demo.repository.BonusAccountRepository;
import com.example.demo.repository.PersonalAccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class DemoControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private BonusAccountRepository bonusAccountRepository;
    @Autowired
    private PersonalAccountRepository personalAccountRepository;

    @BeforeEach
    void setUp() {
        PersonalAccount personalAccount = new PersonalAccount();
        personalAccount.setBalance(new BigDecimal(5000));
        personalAccountRepository.save(personalAccount);
        BonusAccount bonusAccount = new BonusAccount();
        bonusAccount.setBalance(BigDecimal.ZERO);
        bonusAccountRepository.save(bonusAccount);
    }

    @AfterEach
    void tearDown() {
        personalAccountRepository.deleteAll();
        bonusAccountRepository.deleteAll();
    }

    @Test
    void purchaseTest() throws Exception {
        doPurchase(ShopType.ONLINE, new BigDecimal(100));
        doPurchase(ShopType.SHOP, new BigDecimal(120));
        doPurchase(ShopType.ONLINE, new BigDecimal(301));
        doPurchase(ShopType.ONLINE, new BigDecimal(17));
        doPurchase(ShopType.SHOP, new BigDecimal(1000));
        doPurchase(ShopType.ONLINE, new BigDecimal(21));
        doPurchase(ShopType.SHOP, new BigDecimal(570));
        doPurchase(ShopType.ONLINE, new BigDecimal(700));
        mvc.perform(MockMvcRequestBuilders.get("/api/money"))
                .andExpect(status().isOk())
                .andExpect(content().string("2171.00"));
        mvc.perform(MockMvcRequestBuilders.get("/api/bankAccountOfEMoney"))
                .andExpect(status().isOk())
                .andExpect(content().string("802.17"));
    }

    private void doPurchase(ShopType shopType, BigDecimal amount) throws Exception {
        URI uri = UriComponentsBuilder.fromPath("/api/payment")
                .pathSegment(shopType.toString(), amount.toString())
                .build()
                .toUri();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri);
        mvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

}