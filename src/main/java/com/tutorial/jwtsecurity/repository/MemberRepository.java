package com.tutorial.jwtsecurity.repository;

import com.tutorial.jwtsecurity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberID(String memberID);

    boolean existsByMemberID(String memberID);

    int deleteByMemberID(String memberID);

}
