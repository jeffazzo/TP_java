package com.udemy.sharebook.book;

import com.udemy.sharebook.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;

    @Size(min = 5, max = 25, message = "Le nom du livre doit faire entre 5 et 25 carcatères")
    private String name;

    @Size(min = 5, max = 25, message = "La catégorie du livre doit faire entre 5 et 25 carcatères")
    private String category;

    private Boolean deleted;

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book(String bookname) {
        this.name = bookname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
