package com.board.domain.member.entity;

import com.board.domain.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;

    public static Member toEntity(MemberDto memberDto){
        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        return member;
    }
}
