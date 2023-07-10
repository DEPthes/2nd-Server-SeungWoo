package com.board.domain.member.dto;

import com.board.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String email;
    private String password;
    private String name;

    public static MemberDto toDto(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setEmail(member.getEmail());
        memberDto.setPassword(member.getPassword());
        memberDto.setName(member.getName());
        return memberDto;
    }
}
