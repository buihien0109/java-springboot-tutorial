package com.example.jpanew.compositeId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_order")
public class Order {
    @EmbeddedId
    private OrderIds orderIds;

    private Integer total;

    public Order(Integer total) {
        this.total = total;
    }
}