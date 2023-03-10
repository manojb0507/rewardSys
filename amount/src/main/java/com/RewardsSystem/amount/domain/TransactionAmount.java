package com.RewardsSystem.amount.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name ="transaction_amount")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionAmount {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_Id")
    private Long transactionId;

    @Column(name="user_id")
    private Long userId;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    @Column(name = "transaction_amount_usr")
    private double transactionAmountUsr;




}
