package com.tutorial.jwtsecurity.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TODO")
@DynamicUpdate
@Entity
public class TODO extends BaseTimeEntry{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private TODOStatus todoStatus;

    private String content;

    private String memberID;

    public void setTodoStatus(TODOStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    @Builder
    public TODO(long id,TODOStatus todoStatus,String memberID,String content){

        this.id=id;
        this.todoStatus=todoStatus;
        this.memberID=memberID;
        this.content=content;

    }


}
