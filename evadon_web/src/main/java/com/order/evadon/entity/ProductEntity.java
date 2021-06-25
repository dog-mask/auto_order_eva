package com.order.evadon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "PRODUCT")
@Getter@Setter
public class ProductEntity {

    @Id
    int id;
    String name;
    int quantity;
    int orderQuantity;
}
