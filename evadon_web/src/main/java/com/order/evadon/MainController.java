package com.order.evadon;


import com.order.evadon.entity.ProductEntity;
import com.order.evadon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
public class MainController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String main(Model model) {

        model.addAttribute("productList", productRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));


        return "index";
    }

    @GetMapping("/detail")
    public String detail(Model model, int id) {
        // id는 get 파라미터로 넘어온 값
        // @PathVariable 은 주소값 뒤에 {id} 형태로 넣은 것. ,@RequestParam 은 get 파라미터값을 받는건데 생략 가능
        model.addAttribute("product", productRepository.getById(id));
        return "productDetail";
    }
    
}
