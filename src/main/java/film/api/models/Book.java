package film.api.models;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import  java.util.*;
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<BookAuthor> bookAuthors = new ArrayList<>();
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String bookName;
//
//    @ManyToMany(mappedBy = "books")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Set<Author> authors = new HashSet<>();
//
//    // constructors, getters, setters
}

