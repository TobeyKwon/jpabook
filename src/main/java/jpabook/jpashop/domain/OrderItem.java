package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.*;

import javax.persistence.*;

@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice; // 주문 가격
    private int count;  // 주문 수량

    //== 생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .count(count)
                .build();
        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel() {
        item.addStock(count);
    }

    //==조회 로직==//
    public int getTotalPrice() {
        return orderPrice * count;
    }
}
