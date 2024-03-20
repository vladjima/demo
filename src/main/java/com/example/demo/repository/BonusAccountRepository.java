package com.example.demo.repository;

import com.example.demo.entity.BonusAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusAccountRepository extends JpaRepository<BonusAccount, Long> {
}
