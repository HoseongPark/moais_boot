package com.tutorial.jwtsecurity.controller;

import com.tutorial.jwtsecurity.controller.dto.MemberResponseDto;
import com.tutorial.jwtsecurity.controller.dto.TODORequestDto;
import com.tutorial.jwtsecurity.controller.dto.TODOResponseDto;
import com.tutorial.jwtsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @PostMapping("/leave")
    public ResponseEntity<?> leave() {
        return ResponseEntity.ok(memberService.upLeave());
    }


    @PostMapping("getTODOList")
    public ResponseEntity<?> getTODOList(@RequestBody TODORequestDto todoRequestDto){

        switch (todoRequestDto.getOption()){
            case "list" :
                return ResponseEntity.ok(memberService.getTODOList(todoRequestDto));
            case "Lately":
                return ResponseEntity.ok(memberService.getLatelyTODO(todoRequestDto));
            default:
                return null;
        }

    }

    @PostMapping("insertTODO")
    public ResponseEntity<TODOResponseDto> insertTODO(@RequestBody TODORequestDto todoRequestDto){

        return ResponseEntity.ok(memberService.insertTODO(todoRequestDto));
    }

    @PostMapping("modifyTODO")
    public  ResponseEntity<?> modifyTODO(@RequestBody TODORequestDto todoRequestDto){

        return ResponseEntity.ok(memberService.modifyTODO(todoRequestDto));
    }

    @PostMapping("deleteTODO")
    public ResponseEntity<?> deleteTODO(@RequestBody TODORequestDto todoRequestDto){

        return ResponseEntity.ok(memberService.deleteTODO(todoRequestDto));
    }
}