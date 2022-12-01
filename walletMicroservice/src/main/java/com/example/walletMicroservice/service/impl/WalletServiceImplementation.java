package com.example.walletMicroservice.service.impl;

import com.example.walletMicroservice.controller.WalletController;
import com.example.walletMicroservice.exception.PlayerNotFountException;
import com.example.walletMicroservice.models.Players;
import com.example.walletMicroservice.models.Transactions;
import com.example.walletMicroservice.repo.PlayerRepository;
import com.example.walletMicroservice.repo.TransactionRepository;
import com.example.walletMicroservice.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class WalletServiceImplementation implements WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletServiceImplementation.class);
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public ResponseEntity<Players> addPlayer(Players player) {
        try {
            //Check if necessary details are present
            if (null != player.getPlayerId() && player.getPlayerId() > 0 && null != player.getBalance() && player.getBalance() >= 0) {
                Players response = playerRepository.save(player);
                logger.info("Player added successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                logger.error("Insufficient or Incorrect Data");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Transactions>> getTransactionHistory(Long player_id) {
        try {
            if (null != player_id && player_id > 0) {
                List<Transactions> transactions = transactionRepository.findByPlayerId(player_id);
                Collections.sort(transactions, (transactions1, transactions2) ->
                        transactions2.getCreatedAt().compareTo(transactions1.getCreatedAt()));
                if (transactions.isEmpty()) {
                    logger.error("No transactions found for the given Player ID");
                    throw new PlayerNotFountException();
                }
                logger.info("Successfully retrieved transaction for given player id");
                return new ResponseEntity<>(transactions, HttpStatus.OK);
            } else {
                logger.error("Insufficient or Incorrect Data");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (PlayerNotFountException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Players> getPlayerBalance(Long player_id) {
        try {
            if (null != player_id && player_id > 0) {
                Optional<Players> player = playerRepository.findById(player_id);
                if (player.isPresent()) {
                    logger.info("Player balance retrieved successfully ");
                    return new ResponseEntity<>(player.get(), HttpStatus.OK);
                } else {
                    logger.error("Given player id does not exist");
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                logger.error("Insufficient or Incorrect Data");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Transactions> debitTransaction(Transactions debitTransaction) {
        try {
            if ( null != debitTransaction.getTransactionAmount()
                    && debitTransaction.getTransactionAmount() >0
                    && null != debitTransaction.getPlayerId()) {
                //Get current balance
                Players currentPlayer = playerRepository.findByPlayerId(debitTransaction.getPlayerId());
                Long currentPlayerBalance = currentPlayer.getBalance();
                //(balance - debit amount >= 0) check
                //Each transactions updates balance in player table
                if (currentPlayerBalance - debitTransaction.getTransactionAmount() >= 0) {
                    Transactions response = transactionRepository
                            .save(new Transactions(UUID.randomUUID().getMostSignificantBits(), debitTransaction.getPlayerId(),
                                    "debit", new Date(), debitTransaction.getTransactionAmount()));
                    playerRepository.save(new Players(response.getPlayerId(), currentPlayerBalance - debitTransaction.getTransactionAmount()));
                    logger.info("debit transaction successfully recorded");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                } else {
                    logger.error("Unable to perform transaction due to insufficient balance");
                    return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Transactions> creditTransaction(Transactions creditTransaction) {
        try {
            if ( null != creditTransaction.getTransactionAmount()
                    && creditTransaction.getTransactionAmount() > 0
                    && null != creditTransaction.getPlayerId()) {
                Players currentPlayer = playerRepository.findByPlayerId(creditTransaction.getPlayerId());
                Long currentPlayerBalance = currentPlayer.getBalance();
                Transactions response = transactionRepository
                        .save(new Transactions(UUID.randomUUID().getMostSignificantBits(), creditTransaction.getPlayerId(),
                                "credit", new Date(), creditTransaction.getTransactionAmount()));
                playerRepository.save(new Players(response.getPlayerId(), currentPlayerBalance + creditTransaction.getTransactionAmount()));
                logger.info("credit transaction successfully recorded");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
