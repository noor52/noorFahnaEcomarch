package com.noor170.culturalpro.factory;

import com.noor170.culturalpro.model.Product;
import com.noor170.culturalpro.model.Product.ProductType;

public class GarmentFactory {

    public static Product create(String type, String name, Double price, String size, String image) {
        ProductType productType;

        try {
            productType = ProductType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown garment type: " + type + ". Supported types: SHIRT, PANT");
        }

        return new Product(name, productType, price, size, image);
    }
}
