package com.udemy.sharebook.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByStatusAndUserIdNotAndDeletedFalse(String status, Integer userId);

    List<Book> findByUserIdAndDeletedFalse(Integer id);

}
