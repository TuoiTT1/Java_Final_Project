package com.vn.book_store_server.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtRequest implements Serializable {

    private String userName;
    private String password;

    public  JwtRequest(){

    }
    public JwtRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
