package jpabook.jpashop.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Book extends Item {
    private String director;
    private String actor;
}
