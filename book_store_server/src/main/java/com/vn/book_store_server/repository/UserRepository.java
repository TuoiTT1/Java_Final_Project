package com.vn.book_store_server.repository;

import com.vn.book_store_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUserName(String userName);
    Boolean existsByEmail(String email);
    Boolean existsByUserName(String userName);
}
