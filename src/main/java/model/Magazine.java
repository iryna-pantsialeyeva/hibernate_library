package model;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class Magazine extends Printable {
    @Setter
    private int issue;

    public Magazine(String name, int issue) {
        super(name);
        this.issue = issue;
    }
}
