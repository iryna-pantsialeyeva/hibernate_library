package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends Printable {
    @Embedded
    @Setter
    private Author author;

    public Book(String name, Publisher publisher, Author author) {
        super(name, publisher);
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" + super.toString() +
                "author=" + author +
                "}";
    }
}
