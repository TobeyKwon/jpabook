package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor @Getter
@ToString
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
