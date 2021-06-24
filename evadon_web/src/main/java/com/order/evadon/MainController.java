package com.order.evadon;


import com.order.evadon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String main(Model model) {

        model.addAttribute("productList",productRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));

        return "index";
    }
}
