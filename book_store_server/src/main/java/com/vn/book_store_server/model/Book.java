package com.vn.book_store_server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    Category category;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String author;
    @Column(name = "Year_Of_Publisher")
    private Integer yearOfPublisher;
    private String publisher;
    private Float price;
    private Integer quantity;

    @Override
    public String toString() {
        return "Book{" +
                "category=" + category +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublisher=" + yearOfPublisher +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
