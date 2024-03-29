package com.board.domain.member;

import com.board.domain.board.BoardService;
import com.board.domain.board.dto.BoardDto;
import com.board.domain.member.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final BoardService boardService;

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute MemberDto memberDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            System.out.println("회원 가입 형식이 올바르지 않습니다.");
            return "join";
        }
        memberService.join(memberDto);
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session, @PageableDefault(page = 1) Pageable pageable, Model model){ // 세션 사용
        MemberDto loginResult = memberService.login(memberDto);
        if(loginResult != null){
            session.setAttribute("loginEmail", loginResult.getEmail());
            session.setAttribute("memberName", loginResult.getName());

            List<BoardDto> boardDtoList = boardService.findAll();
            model.addAttribute("boardList",boardDtoList);

            Page<BoardDto> boardList = boardService.paging(pageable);
            int blockLimit = 3;
            int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
            int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

            model.addAttribute("boardList", boardList);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);

            return "paging";
        } else{
            System.out.println("아이디 혹은 비밀번호를 잘못 입력했습니다.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }

    @PostMapping("email-check")
    public @ResponseBody String emailCheck(@RequestParam("email") String email){
        String result = memberService.emailCheck(email);
        return result;
    }
}
