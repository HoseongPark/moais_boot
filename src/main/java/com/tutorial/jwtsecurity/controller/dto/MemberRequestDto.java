package com.tutorial.jwtsecurity.controller.dto;

import com.tutorial.jwtsecurity.entity.Authority;
import com.tutorial.jwtsecurity.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String memberID;
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberID(memberID)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(memberID, password);
    }
}
