package com.order.evadon.entity;

import com.order.evadon.QuanDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "product")
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
public class ProductEntity {

    @Id
    private int id;
    private String name;
    private int quantity;
    @Column(name = "order_quantity")
    private int orderQuantity;
    @Column(name = "order_code")
    private String orderCode;
    @Column(name = "oneday_need")
    private int onedayNeed;
    @Column(name = "necessary_quantity")
    private int necessaryQuantity;


    public void popOneQuantity() {
       this.quantity -= 1;
    }

    public void addOneQuantity() {
        this.quantity += 1;
    }





    private ProductEntity(ProductBuilder productBuilder) {
        this.id = productBuilder.id;
        this.name = productBuilder.name;
        this.quantity = productBuilder.quantity;
        this.orderQuantity = productBuilder.orderQuantity;
        this.orderCode = productBuilder.orderCode;
        this.onedayNeed = productBuilder.onedayNeed;
        this.necessaryQuantity = productBuilder.necessaryQuantity;
    }


    public static class ProductBuilder {
        private int id;
        private String name;
        private int quantity;
        private int orderQuantity;
        private String orderCode;
        private int onedayNeed;
        private int necessaryQuantity;

        public ProductBuilder(QuanDTO quanDTO) {
            this.id = quanDTO.getId();
            this.name = quanDTO.getName();
            this.quantity = quanDTO.getQuantity();
            this.orderQuantity = quanDTO.getOrderQuantity();
            this.orderCode = quanDTO.getCode();
            this.onedayNeed = quanDTO.getOnedayNeed();
            this.necessaryQuantity = quanDTO.getNecessaryQuantity();
        }

        public ProductEntity build() {
            return new ProductEntity(this);
        }


    }


}
