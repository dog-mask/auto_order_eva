package com.order.evadon;

import com.order.evadon.entity.ProductEntity;
import com.order.evadon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ModyQuanController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/pop")
    public int pop(@RequestBody QuanDTO quanDTO) { // 재고수량 차감

        // id값으로 가져온 entity에 quantity -1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            int quantity = productEntity1.getQuantity();
        // quantity가 0보다 클때만 quantity -1
            if(quantity > 0) {
                productEntity1.setQuantity(quantity - 1);
            }
            productRepository.save(productEntity1);
        });

        productRepository.flush();

        // id값으로 갱신된 quantity 불러옴 만약 불러오는데 실패하면 -1을 리턴
        int result = -1;

        if (productRepository.findById(quanDTO.getId()).isPresent()){
            return productRepository.findById(quanDTO.getId()).get().getQuantity();
        }

        return result;
    }


    @PostMapping("/add")
    public int add(@RequestBody QuanDTO quanDTO) {
        // id값으로 가져온 entity에 quantity +1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            int quantity = productEntity1.getQuantity();
            productEntity1.setQuantity(quantity + 1);
            productRepository.save(productEntity1);
        });

        productRepository.flush();

        // id값으로 갱신된 quantity 불러옴 만약 불러오는데 실패하면 -1을 리턴
        int result = -1;

        if (productRepository.findById(quanDTO.getId()).isPresent()){
            return productRepository.findById(quanDTO.getId()).get().getQuantity();
        }

        return result;
    }

    @PostMapping("/get_db")
    public List<ProductEntity> getDb() { // DB에 있는 모든 재고 정보 긁어옴
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @PostMapping("/add-options")
    public int addOption(@RequestBody QuanDTO quanDTO) {
        // id값으로 가져온 entity에 quantity +1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            int onedayNeed = productEntity1.getOnedayNeed();
            productEntity1.setOnedayNeed(onedayNeed + 1);
            productRepository.save(productEntity1);
        });

        productRepository.flush();

        // id값으로 갱신된 quantity 불러옴 만약 불러오는데 실패하면 -1을 리턴
        int result = -1;

        if (productRepository.findById(quanDTO.getId()).isPresent()){
            return productRepository.findById(quanDTO.getId()).get().getOnedayNeed();
        }

        return result;
    }

    @PostMapping("/pop-options")
    public int popOptions(@RequestBody QuanDTO quanDTO) {
        // id값으로 가져온 entity에 quantity -1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            int onedayNeed = productEntity1.getOnedayNeed();
            productEntity1.setOnedayNeed(onedayNeed - 1);
            productRepository.save(productEntity1);
        });

        productRepository.flush();

        // id값으로 갱신된 quantity 불러옴 만약 불러오는데 실패하면 -1을 리턴
        int result = -1;

        if (productRepository.findById(quanDTO.getId()).isPresent()){
            return productRepository.findById(quanDTO.getId()).get().getOnedayNeed();
        }

        return result;
    }
}
