package com.vn.book_store_server.service;

import com.vn.book_store_server.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO saveDto(UserDTO userDTO);
    boolean existsByUsername(String userName);
    boolean existsByEmail(String email);
}
