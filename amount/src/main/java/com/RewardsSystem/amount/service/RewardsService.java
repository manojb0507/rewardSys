package com.RewardsSystem.amount.service;

import com.RewardsSystem.amount.constant.constant;
import com.RewardsSystem.amount.domain.TransactionAmount;
import com.RewardsSystem.amount.model.Rewards;
import com.RewardsSystem.amount.repository.TransactionAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RewardsService {
    @Autowired
    TransactionAmountRepository transactionRepository;

    public Rewards getRewardsByCustomerId(Long userId) {

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(constant.daysInMonths);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*constant.daysInMonths);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*constant.daysInMonths);

        List<TransactionAmount> lastMonthTransactions = transactionRepository.findAllByUserIdAndTransactionDate(
                userId, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<TransactionAmount> lastSecondMonthTransactions = transactionRepository
                .findAllByUserIdAndTransactionDate(userId, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<TransactionAmount> lastThirdMonthTransactions = transactionRepository.findAllByUserIdAndTransactionDate(userId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
        Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
        Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        Rewards customerRewards = new Rewards();
        customerRewards.setUserId(userId);
        customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
        customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
        customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
        customerRewards.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

        return customerRewards;

    }

    private Long getRewardsPerMonth(List<TransactionAmount> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction))
                .collect(Collectors.summingLong(r -> r.longValue()));
    }

    private Long calculateRewards(TransactionAmount t) {
        if (t.getTransactionAmountUsr() > constant.firstRewardLimit && t.getTransactionAmountUsr() <= constant.secondRewardLimit) {
            return Math.round(t.getTransactionAmountUsr() - constant.firstRewardLimit);
        } else if (t.getTransactionAmountUsr() > constant.secondRewardLimit) {
            return Math.round(t.getTransactionAmountUsr() - constant.secondRewardLimit) * 2
                    + (constant.secondRewardLimit - constant.firstRewardLimit);
        } else
            return 0l;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }


}
