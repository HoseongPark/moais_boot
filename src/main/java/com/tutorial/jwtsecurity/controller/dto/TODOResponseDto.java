package com.tutorial.jwtsecurity.controller.dto;

import com.tutorial.jwtsecurity.entity.TODO;
import com.tutorial.jwtsecurity.entity.TODOStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TODOResponseDto {
    private long id;
    private String memberID;
    private TODOStatus todoStatus;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public static TODOResponseDto of(TODO todo){
        return new TODOResponseDto(todo.getId(),todo.getMemberID(),todo.getTodoStatus(),todo.getContent(),todo.getCreateDate(),todo.getModifiedDate());
    }
}
