package com.board.domain.member;

import com.board.domain.board.BoardService;
import com.board.domain.board.dto.BoardDto;
import com.board.domain.member.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    //
    private final BoardService boardService;

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute MemberDto memberDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "join";
        }
        memberService.join(memberDto);
        return "login";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute MemberDto memberDto, HttpSession session){ // 세션 사용
//        MemberDto loginResult = memberService.login(memberDto);
//        if(loginResult != null){
//            session.setAttribute("loginEmail", loginResult.getEmail());
////            session.setAttribute("memberName", loginResult.getName());
//            return "main";
//        } else{
//            return "login";
//        }
//    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session, Model model){ // 세션 사용
        MemberDto loginResult = memberService.login(memberDto);
        if(loginResult != null){
            session.setAttribute("loginEmail", loginResult.getEmail());
            session.setAttribute("memberName", loginResult.getName());

            List<BoardDto> boardDtoList = boardService.findAll();
            model.addAttribute("boardList",boardDtoList);
            return "list";
        } else{
            return "login";
        }
    }


}
