package com.example.walletMicroservice.controller;

import com.example.walletMicroservice.exception.PlayerNotFountException;
import com.example.walletMicroservice.models.Players;
import com.example.walletMicroservice.models.Transactions;
import com.example.walletMicroservice.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);
    @Autowired
    private WalletService walletService;

    //method to add player
    @PostMapping("/addPlayer")
    public ResponseEntity<Players> addPlayer(@RequestBody Players player){
        logger.info("*** Add Player Service Begins ***");
        return walletService.addPlayer(player);
    }

    //method to get all transactions for given player id
    @GetMapping("/getTransactionHistory/{player_id}")
    public ResponseEntity<List<Transactions>> getTransactionHistory(@PathVariable("player_id") Long player_id)  {
        logger.info("*** Get Transaction History Service Begins ***");
        return walletService.getTransactionHistory(player_id);
    }

    //method to get the balance of a player
    @GetMapping("/getPlayerBalance/{player_id}")
    public ResponseEntity<Players> getPlayerBalance(@PathVariable("player_id") Long player_id){
        logger.info("*** Get Player Balance Service Begins ***");
        return walletService.getPlayerBalance(player_id);
    }

    //method for performing debit transaction
    @PostMapping("/debitTransaction")
    public ResponseEntity<Transactions> debitTransaction(@RequestBody Transactions debitTransaction){
        logger.info("*** Debit Transaction Service Begins ***");
        return walletService.debitTransaction(debitTransaction);
    }

    //method for performing credit transaction
    @PostMapping("/creditTransaction")
    public ResponseEntity<Transactions> creditTransaction(@RequestBody Transactions creditTransaction){
        logger.info("*** Credit Transaction Service Begins ***");
        return walletService.creditTransaction(creditTransaction);
    }
}
