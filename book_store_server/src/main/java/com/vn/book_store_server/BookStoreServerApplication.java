package com.vn.book_store_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookStoreServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreServerApplication.class, args);
    }
}
