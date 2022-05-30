package com.example.demo.controller;

import com.example.demo.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("discountProducts", shopService.getProductDiscount(4));
        model.addAttribute("sellProducts", shopService.getProductSell(4));
        return "index";
    }

    @GetMapping("/buy")
    public String getAbout() {
        return "buy";
    }

    @GetMapping("/contact")
    public String getContact(Model model) {
        model.addAttribute("shopInfo", shopService.getShopInfo());
        return "contact";
    }

    @GetMapping("/shop")
    public String getShop(Model model) {
        model.addAttribute("products", shopService.getAll());
        return "shop";
    }
}
