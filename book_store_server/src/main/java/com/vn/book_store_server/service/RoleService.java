package com.vn.book_store_server.service;

import com.vn.book_store_server.dto.RoleDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    RoleDTO findByName(String name);
}
