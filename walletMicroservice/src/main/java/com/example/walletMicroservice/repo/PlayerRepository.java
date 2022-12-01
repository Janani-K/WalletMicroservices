package com.example.walletMicroservice.repo;

import com.example.walletMicroservice.models.Players;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Players, Long> {
    //fetch player details with given player id
    Players findByPlayerId(Long playerId);

}
