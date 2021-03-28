package com.vn.book_store_server.service.mapper;

import com.vn.book_store_server.dto.UserDTO;
import com.vn.book_store_server.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserMapper {
     @Mapping(source = "roles", target = "roles")
     User toModel(UserDTO userDTO);

     @Mapping(source = "roles", target = "roles")
     UserDTO toDTO(User user);

     default User fromUserName(String userName){
          if(userName == null)
               return null;
          User user = new User();
          user.setUserName(userName);

          return  user;
     }
}
