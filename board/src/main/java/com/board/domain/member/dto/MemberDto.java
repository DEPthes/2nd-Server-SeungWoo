package com.board.domain.member.dto;

import com.board.global.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String userName;
    private MemberRole role;
}
