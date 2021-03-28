package com.vn.book_store_server.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleDTO implements Serializable {

    private Integer id;
    private String name;

}
