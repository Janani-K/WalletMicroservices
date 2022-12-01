package com.example.walletMicroservice.repo;

import com.example.walletMicroservice.models.Players;
import com.example.walletMicroservice.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    //Fetch all transactions with given player id
    List<Transactions> findByPlayerId(Long playerId);
}
