package com.order.evadon;

import com.order.evadon.entity.ProductEntity;
import com.order.evadon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class ModyQuanController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/pop")
    public int pop(@RequestBody QuanDTO quanDTO) {

        System.out.println(quanDTO.getId());
        int id = quanDTO.getId();



        Optional<ProductEntity> byId = productRepository.findById(id);
        System.out.println(byId.isEmpty());
        byId.ifPresent(productEntity1 -> {

            int quantity = productEntity1.getQuantity();
//            if(quantity != 0) {
                productEntity1.setQuantity(quantity - 1);
//            }
            productRepository.save(productEntity1);
        });
        productRepository.flush();
        Optional<ProductEntity> byId1 = productRepository.findById(id);
        System.out.println(byId1);
        int result = 0;
        if (byId1.isPresent()){
            result = byId1.get().getQuantity();
        }


        System.out.println(result);

        return result;
    }
}
