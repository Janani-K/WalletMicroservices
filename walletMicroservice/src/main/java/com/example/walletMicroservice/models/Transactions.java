package com.example.walletMicroservice.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    private Long transactionId;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "transaction_amount")
    private Long transactionAmount;

    public Transactions(){

    }

    public Transactions(Long transactionId, Long playerId, String transactionType, Date createdAt, Long transactionAmount) {
        this.transactionId = transactionId;
        this.playerId = playerId;
        this.transactionType = transactionType;
        this.createdAt = createdAt;
        this.transactionAmount = transactionAmount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", playerId=" + playerId +
                ", transaction_type='" + transactionType + '\'' +
                ", createdAt=" + createdAt +
                ", transactionAmount=" + transactionAmount +
                '}';
    }
}
