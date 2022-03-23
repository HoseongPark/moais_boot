package com.tutorial.jwtsecurity.service;

import com.tutorial.jwtsecurity.controller.dto.MemberResponseDto;
import com.tutorial.jwtsecurity.controller.dto.TODORequestDto;
import com.tutorial.jwtsecurity.controller.dto.TODOResponseDto;
import com.tutorial.jwtsecurity.entity.Member;
import com.tutorial.jwtsecurity.entity.TODO;
import com.tutorial.jwtsecurity.entity.TODOStatus;
import com.tutorial.jwtsecurity.repository.MemberRepository;
import com.tutorial.jwtsecurity.repository.TODORepository;
import com.tutorial.jwtsecurity.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TODORepository todoRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String member_id) {
        return memberRepository.findByMemberID(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        return memberRepository.findByMemberID(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    @Transactional
    public int upLeave(){
        return memberRepository.deleteByMemberID(SecurityUtil.getCurrentMemberId());
    }

    @Transactional
    public List<TODO> getTODOList(TODORequestDto todoRequestDto){

        Member member=memberRepository.findByMemberID(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        return todoRepository.findByMemberID(member.getMemberID());
    }

    @Transactional
    public TODOResponseDto getLatelyTODO(TODORequestDto todoRequestDto){
        Member member=memberRepository.findByMemberID(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        return TODOResponseDto.of(todoRepository.findTopByMemberID(member.getMemberID(),Sort.by("createDate")).get());
    }

    @Transactional
    public TODOResponseDto insertTODO(TODORequestDto todoRequestDto){

        Member member=memberRepository.findByMemberID(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        TODO todo=TODO.builder()
                .memberID(member.getMemberID())
                .content(todoRequestDto.getContent())
                .todoStatus(TODOStatus.TODO)
                .build();

        return TODOResponseDto.of(todoRepository.save(todo));

    }

    @Transactional
    public TODOResponseDto modifyTODO(TODORequestDto todoRequestDto){
        Member member=memberRepository.findByMemberID(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        TODO todo=todoRepository.getOne(todoRequestDto.getId());

        if(todo.getMemberID().equals(member.getMemberID())){
            todo.setTodoStatus(todoRequestDto.getTodoStatus());
            return TODOResponseDto.of(todoRepository.save(todo));
        }else{
             return null;
        }

    }

    @Transactional
    public boolean deleteTODO(TODORequestDto todoRequestDto){
        Member member=memberRepository.findByMemberID(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        TODO todo=todoRepository.getOne(todoRequestDto.getId());

        if(todo.getMemberID().equals(member.getMemberID())){
            todoRepository.deleteById(todo.getId());
            return true;
        }else{
            return false;
        }

    }

}
