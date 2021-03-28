package com.vn.book_store_server.dto;

import com.vn.book_store_server.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserDTO implements Serializable {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Set<RoleDTO> roles;

    public UserDTO() {

    }

    public UserDTO(String userName, String password, String firstName, String lastName, String email, Set<RoleDTO> roles) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }
}
