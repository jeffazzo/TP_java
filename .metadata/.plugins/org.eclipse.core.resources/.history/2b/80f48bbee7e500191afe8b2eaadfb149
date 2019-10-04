package com.udemy.sharebook.book;

import com.udemy.sharebook.loan.Loan;
import com.udemy.sharebook.loan.LoanRepository;
import com.udemy.sharebook.user.User;
import com.udemy.sharebook.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;

    // afficher les livres disponibles
    @GetMapping(value = "/users/{userId}/books/status/{status}")
    public List<Book> getBookWithStatus(@PathVariable("userId") String userId,
                                        @PathVariable("status") String statusStr) {

        List<Book> listBook = bookRepository.findByStatusAndUserIdNotAndDeletedFalse(statusStr, Integer.valueOf(userId));
        return listBook;

    }

    // get my books
    @GetMapping(value = "/users/{userId}/books")
    public List<Book> getMyBooks(@PathVariable("userId") String userId) {

        List<Book> listBook = bookRepository.findByUserIdAndDeletedFalse(Integer.valueOf(userId));
        return listBook;


    }

    // creer un livre
    @PostMapping(value = "/users/{userId}/books")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Book createBookFoorUser(@PathVariable("userId") String userId, @Valid @RequestBody Book book) {
        Optional<User> user = userRepository.findById(Integer.valueOf(userId));
        book.setDeleted(false);
        book.setStatus("FREE");
        book.setUser(user.get());
        bookRepository.save(book);
        return book;
    }

    // supprimer un livre
    @DeleteMapping(value = "/books/{bookId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity deleteBook(@PathVariable("bookId") String bookId) {

        Book book = this.bookRepository.findById(Integer.valueOf(bookId)).get();
        List<Loan> loans = this.loanRepository.findByBookId(Integer.valueOf(bookId));

        for( Loan loan : loans) {
            if(loan.getCloseDate() == null) {
                return new ResponseEntity(HttpStatus.CONFLICT);
            }

        }

        book.setDeleted(true);
        bookRepository.save(book);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
