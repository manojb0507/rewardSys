package com.RewardsSystem.amount.repository;

import com.RewardsSystem.amount.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Long, User> {

       User findByTheUserId(Long userId);
}
