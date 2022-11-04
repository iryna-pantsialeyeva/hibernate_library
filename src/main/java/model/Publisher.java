package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
    private String publisherName;
    @Embedded
    private Address publisherAddress;
}
