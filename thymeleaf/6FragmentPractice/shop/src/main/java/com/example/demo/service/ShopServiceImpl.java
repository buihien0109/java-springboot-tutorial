package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.ShopInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    private List<Product> products;

    public ShopServiceImpl() {
        init();
    }

    @Override
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1, "Sản phẩm 1", 450000, 400000, "https://images.unsplash.com/photo-1434389677669-e08b4cac3105?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8Y2xvdGhlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", 10));
        products.add(new Product(2, "Sản phẩm 2", 310000, 0, "https://images.unsplash.com/photo-1467043237213-65f2da53396f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8Y2xvdGhlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60", 14));
        products.add(new Product(3, "Sản phẩm 3", 250000, 150000, "https://images.unsplash.com/photo-1591047139829-d91aecb6caea?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 7));
        products.add(new Product(4, "Sản phẩm 4", 460000, 0, "https://images.unsplash.com/photo-1532453288672-3a27e9be9efd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 9));
        products.add(new Product(5, "Sản phẩm 5", 220000, 0, "https://images.unsplash.com/photo-1516762689617-e1cffcef479d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTZ8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 0));
        products.add(new Product(6, "Sản phẩm 6", 550000, 450000, "https://images.unsplash.com/photo-1554568218-0f1715e72254?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjF8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 16));
        products.add(new Product(7, "Sản phẩm 7", 350000, 0, "https://images.unsplash.com/photo-1580682312385-e94d8de1cf3c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjN8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 20));
        products.add(new Product(8, "Sản phẩm 8", 450000, 300000, "https://images.unsplash.com/photo-1617331721458-bd3bd3f9c7f8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzR8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 30));
        products.add(new Product(9, "Sản phẩm 9", 600000, 0, "https://images.unsplash.com/photo-1525507119028-ed4c629a60a3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mzd8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 15));
        products.add(new Product(10, "Sản phẩm 10", 550000, 500000, "https://images.unsplash.com/photo-1614676471928-2ed0ad1061a4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDB8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 4));
        products.add(new Product(11, "Sản phẩm 11", 450000, 0, "https://images.unsplash.com/photo-1578932750294-f5075e85f44a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDF8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 14));
        products.add(new Product(12, "Sản phẩm 12", 500000, 450000, "https://images.unsplash.com/photo-1605450081927-6b40c11c661f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDV8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 8));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public List<Product> getProductDiscount(int count) {
        return products
                .stream()
                .sorted((a, b) -> b.getPriceDiscount() - a.getPriceDiscount())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductSell(int count) {
        return products
                .stream()
                .sorted((a, b) -> b.getSellNumber() - a.getSellNumber())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public ShopInfo getShopInfo() {
        return new ShopInfo("Bùi Hiên Shop", "Cơ sở Tố Hữu: Tầng 12A, tòa nhà Viwaseen Tower, số 48, Tố Hữu, Lê Văn Lương kéo dài, Hà Nội",
                "0344 005 816", "hien@techmaster.vn", "https://www.facebook.com/buihiennnn", "BUIHIEN-SHOP-zalo-0344005816",
                "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.865473837356!2d105.79245041540294!3d20.998028986015047!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135acba87c457db%3A0x9f0688edddb10fbb!2sVIWASEEN+BUILDING!5e0!3m2!1svi!2s!4v1562477259894!5m2!1svi!2s");
    }
}
