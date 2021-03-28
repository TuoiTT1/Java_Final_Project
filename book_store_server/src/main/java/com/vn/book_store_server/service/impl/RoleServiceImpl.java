package com.vn.book_store_server.service.impl;

import com.vn.book_store_server.dto.RoleDTO;
import com.vn.book_store_server.model.Role;
import com.vn.book_store_server.repository.RoleRepository;
import com.vn.book_store_server.service.RoleService;
import com.vn.book_store_server.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    private RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDTO findByName(String name) {
        Role role = roleRepository.findByName(name).orElse(null);
        if (role == null){
            return  null;
        }
        return roleMapper.toDTO(role);
    }
}
