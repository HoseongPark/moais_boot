package com.tutorial.jwtsecurity.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member extends BaseTimeEntry{

    @Id
    private String memberID;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String memberID, String password, Authority authority) {
        this.memberID = memberID;
        this.password = password;
        this.authority = authority;
    }
}
