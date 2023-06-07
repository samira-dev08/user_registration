package com.company.repository;

import com.company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmailIgnoreCase(String userEmail);

    Boolean existsByUserEmail(String userEmail);
    List<User> findUserByStatus(Long status);
}
