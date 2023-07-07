package film.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BookAuthor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
//    private Book book;
//
//    @ManyToOne
//    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false       )
//    private Author author;

    // constructors, getters, setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;
}

