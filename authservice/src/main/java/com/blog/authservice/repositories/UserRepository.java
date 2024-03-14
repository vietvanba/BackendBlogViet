package com.blog.authservice.repositories;

import com.blog.authservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account,String> {
    Boolean existsByEmail(String email);
}
