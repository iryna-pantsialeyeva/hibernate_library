package model;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class Magazine extends Printable {
    @Setter
    private int issue;

    public Magazine(String name, Publisher publisher, int issue) {
        super(name, publisher);
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "Magazine{" + super.toString() +
                "issue=" + issue +
                "}";
    }
}
