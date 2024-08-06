package com.example.demo.Borrow;

import com.example.demo.Book.Model.BookModel;
import com.example.demo.Book.Repository.BookRepository;
import com.example.demo.patron.Model.patronModel;
import com.example.demo.patron.Repository.patronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api")
public class BorrowController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private patronRepository patronRepository;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    @Transactional
    public ResponseEntity<Void> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BookModel book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        patronModel patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found"));

        if (book.getStatus() != BookModel.BookStatus.AVAILABLE) {
            return ResponseEntity.badRequest().build();
        }

        book.setStatus(BookModel.BookStatus.BORROWED);
        bookRepository.save(book);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    @Transactional
    public ResponseEntity<Void> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BookModel book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        patronModel patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found"));

        if (book.getStatus() != BookModel.BookStatus.BORROWED) {
            return ResponseEntity.badRequest().build();
        }

        book.setStatus(BookModel.BookStatus.AVAILABLE);
        bookRepository.save(book);

        return ResponseEntity.ok().build();
    }
}