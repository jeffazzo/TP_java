package com.udemy.sharebook.loan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Integer> {

    List<Loan> findByBorrowerId(Integer borrowerId);

    List<Loan> findByBookId(Integer bookId);

}
