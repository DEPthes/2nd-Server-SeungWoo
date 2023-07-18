package com.board.domain.member;

import com.board.domain.member.dto.MemberDto;
import com.board.domain.member.entity.Member;
import com.board.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberDto memberDto) {
        Member member = Member.toEntity(memberDto);
        memberRepository.save(member);
    }

    public MemberDto login(MemberDto memberDto) {
        Optional<Member> findEmail = memberRepository.findByEmail(memberDto.getEmail());
        if(findEmail.isPresent()){
            Member member = findEmail.get();
            if(member.getPassword().equals(memberDto.getPassword())){
                MemberDto dto = MemberDto.toDto(member);
                return dto;
            } else{
               return null;
            }
        }else{
            return null;
        }
    }

    public String emailCheck(String email) {
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        if(byEmail.isPresent()){
            return null;
        } else {
            return "ok";
        }

    }
}
