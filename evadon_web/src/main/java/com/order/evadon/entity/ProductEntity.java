package com.order.evadon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter@Setter
public class ProductEntity {

    @Id @GeneratedValue
    @Column(name = "id")
    int id;
    String name;
    int quantity;
    int orderQuantity;
}
