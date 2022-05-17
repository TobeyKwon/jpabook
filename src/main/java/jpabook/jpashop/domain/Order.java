package jpabook.jpashop.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED) @Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //== 생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = Order.builder()
                .member(member)
                .delivery(delivery)
                .status(OrderStatus.ORDER)
                .build();

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }
}