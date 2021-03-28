package com.vn.book_store_server.service.mapper;

import com.vn.book_store_server.dto.RoleDTO;
import com.vn.book_store_server.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface RoleMapper {

    Role toModel(RoleDTO roleDTO);

    RoleDTO toDTO(Role role);

    default Role fromId(Integer id){
        if(id == null)
            return null;
        Role role = new Role();
        role.setId(id);

        return role;
    }
}
