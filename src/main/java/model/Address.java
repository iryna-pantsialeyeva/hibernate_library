package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String streetAndBuild;
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Publisher publisher;
    public Address(String streetAndBuild) {
        this.streetAndBuild = streetAndBuild;
    }
}
