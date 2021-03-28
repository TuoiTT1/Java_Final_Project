package com.vn.book_store_server.dto.request;

import com.vn.book_store_server.dto.UserDTO;
import com.vn.book_store_server.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SignupRequest implements Serializable {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;

}
