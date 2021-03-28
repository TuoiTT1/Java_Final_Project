package com.vn.book_store_server.dto.response;

import com.vn.book_store_server.dto.UserDTO;
import com.vn.book_store_server.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class JwtResponse implements Serializable {
    private String jwtToken;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;

    public JwtResponse(){

    }

    public JwtResponse(String jwtToken, String userName, String firstName, String lastName, String email, List<String> roles) {
        this.jwtToken = jwtToken;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }
}
