package com.RewardsSystem.amount.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long userId;

    @Column(name ="user")
    private String userName;

}
