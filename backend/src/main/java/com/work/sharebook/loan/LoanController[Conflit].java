package com.work.sharebook.loan;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.work.sharebook.book.Book;
import com.work.sharebook.book.BookRepository;
import com.work.sharebook.user.User;
import com.work.sharebook.user.UserRepository;

@RestController
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // mes emprunts
    @GetMapping(value = "/users/{userId}/loans")
    public List<Loan> getLoans(@PathVariable("userId") String userId) {

        List<Loan> listLoans = loanRepository.findByBorrowerId(Integer.valueOf(userId));
        return listLoans;
    }

    // Créer un emprunt
    @PostMapping(value = "/users/{userId}/loans/{bookId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Loan createLoan(@PathVariable("userId") String userId,
                           @PathVariable("bookId") String bookId) {

        Optional<User> borrower = userRepository.findById(Integer.valueOf(userId));
        Optional<Book> book = this.bookRepository.findById(Integer.valueOf(bookId));

        if (borrower.isPresent() && book.isPresent()) {
            Book book1 = book.get();
            User lender = book1.getUser();
            Loan loan = new Loan();
            loan.setAskDate(new Date());
            loan.setBook(book1);
            loan.setBorrower(borrower.get());
            loan.setLender(lender);
            loanRepository.save(loan);

            book1.setStatus("USED");
            bookRepository.save(book1);

            return loan;

        }

        return null;

    }

    // Clore un emprunt
    @DeleteMapping(value = "/loans/{loanId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLoan(@PathVariable("loanId") String loanId) {

        Loan loan = this.loanRepository.findById(Integer.valueOf(loanId)).get();
        loan.setCloseDate(new Date());
        loanRepository.save(loan);

        Book book1 = loan.getBook();
        book1.setStatus("FREE");
        bookRepository.save(book1);

    }
}
