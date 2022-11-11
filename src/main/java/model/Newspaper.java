package model;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity

public class Newspaper extends Printable {
    @Setter
    private String type;

    public Newspaper(String name, Publisher publisher, String type) {
        super(name, publisher);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Newspaper{" + super.toString() +
                "type='" + type + '\'' +
                "}";
    }
}
