package com.order.evadon;

import com.order.evadon.entity.ProductEntity;
import com.order.evadon.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ModyQuanController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/pop")
    public String pop(@RequestBody QuanDTO quanDTO) { // 재고수량 1개 차감 버튼
        try {
            productRepository.findById(quanDTO.getId()).ifPresent(productEntity -> {
                // 현재 재고가 0이거나 0보다 작으면 에러 던짐
                if (productEntity.getQuantity() <= 0) {
                    throw new IllegalArgumentException("이미 재고가 0개 입니다.");
                }
                productEntity.popOneQuantity();
                productRepository.save(productEntity);
            });
        }catch (IllegalArgumentException e){
            // 에러 메시지 리턴
            return e.getMessage();
        }

        return "ok";
    }


    @PostMapping("/add")
    public String add(@RequestBody QuanDTO quanDTO) {
        try {
            // id값으로 가져온 entity에 quantity +1 이후 save
            productRepository.findById(quanDTO.getId())
                    .ifPresent(productEntity -> {
                        productEntity.addOneQuantity();
                        productRepository.save(productEntity);
                    });
        } catch (Exception e) {
            logger.error("add버튼 에러 : "+e.getMessage());
            return "문제가 발생 하였습니다. 버튼을 천천히 눌러주세요";
        }
        return "ok";
    }

    @PostMapping("/get_db")
    public List<ProductEntity> getDb() { // DB에 있는 모든 재고 정보 긁어옴

        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @PostMapping("/add-oneday-need")
    public int addOption(@RequestBody QuanDTO quanDTO) {

        // id값으로 가져온 entity에 quantity +1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            int onedayNeed = productEntity1.getOnedayNeed();
//            productEntity1.setOnedayNeed(onedayNeed + 1);
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
    public int popOptions(@RequestBody QuanDTO quanDTO) {
        // id값으로 가져온 entity에 quantity -1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            int onedayNeed = productEntity1.getOnedayNeed();
//            productEntity1.setOnedayNeed(onedayNeed - 1);
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
//            productEntity.setOrderQuantity(quanDTO.getOrderQuantity());
            productRepository.save(productEntity);
        });
    }

    @PostMapping("/pop-necessary-quantity")
    public int popNecessaryQuantity(@RequestBody QuanDTO quanDTO) {
        // id값으로 가져온 entity에 quantity -1 이후 save
        productRepository.findById(quanDTO.getId()).ifPresent(productEntity1 -> {
            int necessaryQuantity = productEntity1.getNecessaryQuantity();
//            productEntity1.setNecessaryQuantity(necessaryQuantity - 1);
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
//            productEntity1.setNecessaryQuantity(necessaryQuantity + 1);
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
//        ProductEntity productEntity = new ProductEntity();
//        productEntity.setName(quanDTO.getName());
//        productEntity.setOrderCode(quanDTO.getCode());
//        productRepository.save(productEntity);
    }

//    @PostMapping("/plus-quan")
//    public void plusQuan(){
//        int quan;
//        int order;
//        int total;
//        List<ProductEntity> all = productRepository.findAll();
//        for (ProductEntity product:all) {
//            quan = product.getQuantity();
//            order = product.getOrderQuantity();
//            total = quan+order;
//            product.setQuantity(total);
//            product.setOrderQuantity(0);
//            productRepository.save(product);
//        }
//    }

    @PostMapping("/findId")
    public ProductEntity findId(@RequestBody QuanDTO quanDTO){
        System.out.println(quanDTO);
        return productRepository.findById(quanDTO.getId()).get();
    }

    @PostMapping("/modyCode")
    public void modyProduct(@RequestBody QuanDTO quanDTO) {
        ProductEntity productEntity1 = productRepository.findById(quanDTO.getId()).get();
//        productEntity1.setOrderCode(quanDTO.getCode());
        productRepository.save(productEntity1);
    }


}
