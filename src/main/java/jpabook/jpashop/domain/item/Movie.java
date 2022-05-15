package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Movie extends Item {
    private String author;
    private String isbn;
}
