package jpabook.jpashop.domain.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
