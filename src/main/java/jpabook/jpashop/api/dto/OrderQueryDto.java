package jpabook.jpashop.api.dto;

import jpabook.jpashop.domain.entity.Address;
import jpabook.jpashop.domain.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }

    public OrderQueryDto(OrderFlatDto orderFlatDto) {
        this.orderId = orderFlatDto.getOrderId();
        this.name = orderFlatDto.getName();
        this.orderDate = orderFlatDto.getOrderDate();
        this.orderStatus = orderFlatDto.getOrderStatus();
        this.address = orderFlatDto.getAddress();
    }
}
