package com.RewardsSystem.amount.repository;

import com.RewardsSystem.amount.domain.TransactionAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public interface TransactionAmountRepository extends JpaRepository<Long,TransactionAmount> {

  List<TransactionAmount> findAllByUserIdAndTransactionDate(Long userId, Timestamp lastMonthTimestamp, Timestamp now);


}
