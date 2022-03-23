package com.tutorial.jwtsecurity.repository;

import com.tutorial.jwtsecurity.entity.TODO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TODORepository extends JpaRepository<TODO,Long> {

    List<TODO> findByMemberID(String memberID);

    Optional<TODO> findTopByMemberID(String memberID, Sort sort);

}
