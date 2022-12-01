package com.example.walletMicroservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Players {

    @Id
    private Long playerId;

    @Column(name = "balance")
    private Long balance;

    public Players(){

    }

    public Players(Long playerId, Long balance) {
        this.playerId = playerId;
        this.balance = balance;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Players{" +
                "playerId=" + playerId +
                ", balance=" + balance +
                '}';
    }
}
