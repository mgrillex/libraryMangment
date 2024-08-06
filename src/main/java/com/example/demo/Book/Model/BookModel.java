package com.example.demo.Book.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "books")
public class BookModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Title is required")
        @Column(nullable = false)
        private String title;

        @NotBlank(message = "Author is required")
        @Column(nullable = false)
        private String author;

        @Min(value = 1000, message = "Publication year must be after 1000")
        @Max(value = 9999, message = "Publication year must be a 4-digit number")
        private int publicationYear;

        @NotBlank(message = "ISBN is required")
        @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
                message = "Invalid ISBN format")
        @Column(unique = true)
        private String isbn;

        @Enumerated(EnumType.STRING)
        private BookStatus status = BookStatus.AVAILABLE;

        public enum BookStatus {
                AVAILABLE, BORROWED
        }

        /////getters and setters\\\\\
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
        public int getPublicationYear() { return publicationYear; }
        public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
        public String getIsbn() { return isbn; }
        public void setIsbn(String isbn) { this.isbn = isbn; }
        public BookStatus getStatus() { return status; }
        public void setStatus(BookStatus status) { this.status = status; }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                BookModel bookModel = (BookModel) o;
                return Objects.equals(id, bookModel.id) && Objects.equals(isbn, bookModel.isbn);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, isbn);
        }

        @Override
        public String toString() {
                return "BookModel{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", author='" + author + '\'' +
                        ", publicationYear=" + publicationYear +
                        ", isbn='" + isbn + '\'' +
                        ", status=" + status +
                        '}';
        }
}