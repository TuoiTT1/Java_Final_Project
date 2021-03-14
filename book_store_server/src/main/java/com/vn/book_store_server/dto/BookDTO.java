package com.vn.book_store_server.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BookDTO implements Serializable {
    private Integer id;
    private String name;
    private String author;
    private Integer yearOfPublisher;
    private String publisher;
    private Float price;
    private Integer quantity;
    private Integer categoryId;

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublisher=" + yearOfPublisher +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", categoryId=" + categoryId +
                '}';
    }
}
