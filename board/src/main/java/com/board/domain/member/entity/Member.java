package com.board.domain.member.entity;

import com.board.global.MemberRole;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "MEMBER_ID")
        private Long id;

        @Column(nullable = false, unique = true)
        private String userName;

//        @Column(nullable = false)
//        private String userPassword;

//        @Column(name = "email")
//        private String email;

        @Column(name = "ROLE", nullable = false)
        @Enumerated(EnumType.STRING)
        private MemberRole role;

        @Builder
        public Member(String userName, MemberRole role) {
                this.userName = userName;
                this.role = role;
        }
}

