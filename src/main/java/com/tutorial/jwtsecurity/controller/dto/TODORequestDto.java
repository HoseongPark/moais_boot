package com.tutorial.jwtsecurity.controller.dto;

import com.tutorial.jwtsecurity.entity.TODOStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TODORequestDto {
    private long id;
    private String memberID;
    private TODOStatus todoStatus;
    private String content;
    private String option;

}
