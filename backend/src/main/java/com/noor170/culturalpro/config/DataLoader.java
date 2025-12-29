package com.noor170.culturalpro.config;

import com.noor170.culturalpro.factory.GarmentFactory;
import com.noor170.culturalpro.model.Product;
import com.noor170.culturalpro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductService productService;

    @Autowired
    public DataLoader(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productService.getAll().isEmpty()) {
            Product shirt1 = GarmentFactory.create("SHIRT", "Classic White Shirt", 29.99, "M",
                "https://images.pexels.com/photos/297933/pexels-photo-297933.jpeg?auto=compress&cs=tinysrgb&w=400");

            Product shirt2 = GarmentFactory.create("SHIRT", "Blue Denim Shirt", 39.99, "L",
                "https://images.pexels.com/photos/1034619/pexels-photo-1034619.jpeg?auto=compress&cs=tinysrgb&w=400");

            Product shirt3 = GarmentFactory.create("SHIRT", "Black Polo Shirt", 34.99, "M",
                "https://images.pexels.com/photos/769733/pexels-photo-769733.jpeg?auto=compress&cs=tinysrgb&w=400");

            Product pant1 = GarmentFactory.create("PANT", "Dark Blue Jeans", 49.99, "32",
                "https://images.pexels.com/photos/1598507/pexels-photo-1598507.jpeg?auto=compress&cs=tinysrgb&w=400");

            Product pant2 = GarmentFactory.create("PANT", "Khaki Chinos", 44.99, "34",
                "https://images.pexels.com/photos/1464625/pexels-photo-1464625.jpeg?auto=compress&cs=tinysrgb&w=400");

            Product pant3 = GarmentFactory.create("PANT", "Black Formal Trousers", 54.99, "32",
                "https://images.pexels.com/photos/7679876/pexels-photo-7679876.jpeg?auto=compress&cs=tinysrgb&w=400");

            productService.save(shirt1);
            productService.save(shirt2);
            productService.save(shirt3);
            productService.save(pant1);
            productService.save(pant2);
            productService.save(pant3);

            System.out.println("Seeded 6 products (3 shirts, 3 pants) using GarmentFactory");
        }
    }
}
