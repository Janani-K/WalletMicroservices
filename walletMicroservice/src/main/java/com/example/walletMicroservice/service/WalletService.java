package com.example.walletMicroservice.service;

import com.example.walletMicroservice.exception.PlayerNotFountException;
import com.example.walletMicroservice.models.Players;
import com.example.walletMicroservice.models.Transactions;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WalletService {

    public ResponseEntity<Players> addPlayer( Players player);

    public ResponseEntity<List<Transactions>> getTransactionHistory(Long player_id) ;

    public ResponseEntity<Players> getPlayerBalance(Long player_id);

    public ResponseEntity<Transactions> debitTransaction(Transactions debitTransaction);

    public ResponseEntity<Transactions> creditTransaction(Transactions debitTransaction);

    //public ResponseEntity<Transactions> transactionEntry(Transactions transaction);

}
