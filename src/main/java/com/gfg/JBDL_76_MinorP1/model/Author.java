package com.gfg.JBDL_76_MinorP1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@IdClass(AuthorCompositeKey.class)
@Builder
public class Author extends TimeStamps{

    @Id
    private String id;

    @Id
    @Column(nullable = false, unique = true, length=20)
    private String email;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
   // @JsonIgnore
    private List<Book> bookList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
