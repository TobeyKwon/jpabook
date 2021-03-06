package jpabook.jpashop.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Album extends Item {
    private String artist;
    private String etc;
}
