package com.order.evadon;

import com.order.evadon.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class QuanDTO {

    private int id;
    private int orderQuantity;
    private int necessaryQuantity;
    private int quantity;
    private int onedayNeed;
    private String name;
    private String code;
}
