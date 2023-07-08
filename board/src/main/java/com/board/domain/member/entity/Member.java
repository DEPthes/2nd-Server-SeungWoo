package com.board.domain.member.entity;

import com.board.global.UserRole;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Member {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(nullable = false, unique = true)
        private String userName;

//        @Column(nullable = false)
//        private String userPassword;

//        @Column(name = "email")
//        private String email;

        @Column(name = "state")
        @Enumerated(EnumType.STRING)
        private UserRole role;


    }

