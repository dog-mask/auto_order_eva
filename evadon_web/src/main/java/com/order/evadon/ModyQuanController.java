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
            if (quantity > 0) {
                productEntity1.setQuantity(quantity - 1);
            }
            productRepository.save(productEntity1);
        });

        productRepository.flush();

        // id값으로 갱신된 quantity 불러옴 만약 불러오는데 실패하면 -1을 리턴
        int result = -1;

        if (productRepository.findById(quanDTO.getId()).isPresent()) {
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

        if (productRepository.findById(quanDTO.getId()).isPresent()) {
            return productRepository.findById(quanDTO.getId()).get().getQuantity();
        }

        return result;
    }

    @PostMapping("/get_db")
    public List<ProductEntity> getDb() { // DB에 있는 모든 재고 정보 긁어옴
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @PostMapping("/add-oneday-need")
    public double addOption(@RequestBody QuanDTO quanDTO) {
        // id값으로 가져온 entity에 quantity +1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            double onedayNeed = productEntity1.getOnedayNeed();
            productEntity1.setOnedayNeed(onedayNeed + 1);
            productRepository.save(productEntity1);
        });

        productRepository.flush();

        // id값으로 갱신된 quantity 불러옴 만약 불러오는데 실패하면 -1을 리턴
        int result = -1;

        if (productRepository.findById(quanDTO.getId()).isPresent()) {
            return productRepository.findById(quanDTO.getId()).get().getOnedayNeed();
        }

        return result;
    }

    @PostMapping("/pop-oneday-need")
    public double popOptions(@RequestBody QuanDTO quanDTO) {
        // id값으로 가져온 entity에 quantity -1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            double onedayNeed = productEntity1.getOnedayNeed();
            productEntity1.setOnedayNeed(onedayNeed - 1);
            productRepository.save(productEntity1);
        });

        productRepository.flush();

        // id값으로 갱신된 quantity 불러옴 만약 불러오는데 실패하면 -1을 리턴
        int result = -1;

        if (productRepository.findById(quanDTO.getId()).isPresent()) {
            return productRepository.findById(quanDTO.getId()).get().getOnedayNeed();
        }

        return result;
    }

    // 발주시 주문수량 DB에 업데이트
    @PostMapping("/init-order-count")
    public void addOrderCount(@RequestBody QuanDTO quanDTO){
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity -> {
            productEntity.setOrderQuantity(quanDTO.getOrderQuantity());
            productRepository.save(productEntity);
        });
    }

    @PostMapping("/pop-necessary-quantity")
    public int popNecessaryQuantity(@RequestBody QuanDTO quanDTO) {
        // id값으로 가져온 entity에 quantity -1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            int necessaryQuantity = productEntity1.getNecessaryQuantity();
            productEntity1.setNecessaryQuantity(necessaryQuantity - 1);
            productRepository.save(productEntity1);
        });

        productRepository.flush();

        // id값으로 갱신된 quantity 불러옴 만약 불러오는데 실패하면 -1을 리턴
        int result = -1;

        if (productRepository.findById(quanDTO.getId()).isPresent()) {
            return productRepository.findById(quanDTO.getId()).get().getNecessaryQuantity();
        }

        return result;
    }

    @PostMapping("/add-necessary-quantity")
    public int addNecessaryQuantity(@RequestBody QuanDTO quanDTO) {
        // id값으로 가져온 entity에 quantity -1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            int necessaryQuantity = productEntity1.getNecessaryQuantity();
            productEntity1.setNecessaryQuantity(necessaryQuantity + 1);
            productRepository.save(productEntity1);
        });

        productRepository.flush();

        // id값으로 갱신된 quantity 불러옴 만약 불러오는데 실패하면 -1을 리턴
        int result = -1;

        if (productRepository.findById(quanDTO.getId()).isPresent()) {
            return productRepository.findById(quanDTO.getId()).get().getNecessaryQuantity();
        }

        return result;
    }

    @PostMapping("/add-product")
    public void addProduct(@RequestBody QuanDTO quanDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(quanDTO.getName());
        productEntity.setOrderCode(quanDTO.getCode());
        productRepository.save(productEntity);
    }

    @PostMapping("/plus-quan")
    public void plusQuan(){
        int quan;
        int order;
        int total;
        List<ProductEntity> all = productRepository.findAll();
        for (ProductEntity product:all) {
            quan = product.getQuantity();
            order = product.getOrderQuantity();
            total = quan+order;
            product.setQuantity(total);
            product.setOrderQuantity(0);
            productRepository.save(product);
        }
    }


}
