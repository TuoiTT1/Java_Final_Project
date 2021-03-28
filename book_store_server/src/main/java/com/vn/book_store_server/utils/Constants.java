package com.vn.book_store_server.utils;

public class Constants {
    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/user";
    public static final String BLANK = "";

    //ROLES
    public static final String ROLE_ADMIN = "admin";
    public static final String ROL_USER = "user";
}
