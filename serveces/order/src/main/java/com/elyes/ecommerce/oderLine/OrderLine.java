package com.elyes.ecommerce.oderLine;


import com.elyes.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @JoinColumn(name = "order_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Order order;

    private Integer productId;
    private double quantity;

}
