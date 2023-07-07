package film.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Other fields

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    // Constructors, getters, setters
}
