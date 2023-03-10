package com.RewardsSystem.amount.controller;

import com.RewardsSystem.amount.domain.User;
import com.RewardsSystem.amount.model.Rewards;
import com.RewardsSystem.amount.repository.UserRepository;
import com.RewardsSystem.amount.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RewardController {

    @Autowired
    RewardsService rewardsService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("userId") long userId){
        User user = userRepository.findByTheUserId(userId);
        if(user == null)
        {
            throw new RuntimeException("Invalid / Missing customer Id ");
        }
        Rewards customerRewards = rewardsService.getRewardsByCustomerId(userId);
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }}




