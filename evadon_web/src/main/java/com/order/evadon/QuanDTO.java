package com.order.evadon;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class QuanDTO {

    private int id;
    private int orderQuantity;
    private int necessaryQuantity;
    private String name;
    private String code;
}
