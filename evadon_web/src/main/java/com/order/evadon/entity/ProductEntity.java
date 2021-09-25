package com.order.evadon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "product")
@Getter@Setter
public class ProductEntity {

    @Id
    int id;
    String name;
    int quantity;
    @Column(name = "order_quantity")
    int orderQuantity;
    @Column(name = "order_code")
    String orderCode;
    @Column(name = "oneday_need")
    double onedayNeed;
    @Column(name = "necessary_quantity")
    int necessaryQuantity;
}
