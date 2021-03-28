package com.vn.book_store_server.service.impl;

import com.vn.book_store_server.dto.UserDTO;
import com.vn.book_store_server.model.User;
import com.vn.book_store_server.repository.UserRepository;
import com.vn.book_store_server.service.UserService;
import com.vn.book_store_server.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(s);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return  UserDetailsImpl.build(user);
    }

    @Override
    public UserDTO saveDto(UserDTO userDTO) {
        User user = userRepository.save(userMapper.toModel(userDTO));
        return userMapper.toDTO(user);
    }

    @Override
    public boolean existsByUsername(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
