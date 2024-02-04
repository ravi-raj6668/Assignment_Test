package org.test.problem3;

import java.util.HashMap;
import java.util.Map;

public class ProductConfiguration {
    private Map<String, Product> productMap = new HashMap<>();//In-Memory storage

    public ProductConfiguration() {
        this.productMap = productMap;
    }

    //add product to the system
    public void addProduct(String productId, String productName, String productCategory, String productDescription){
        Product product = new Product(productName, productCategory, productDescription);
        productMap.put(productId, product);
    }

    //search product
    public void searchProduct(String keyword){
        for(Map.Entry<String, Product> entry : productMap.entrySet()){
            Product product = entry.getValue();
            if(product.getName().contains(keyword) || product.getCategory().contains(keyword)){
                System.out.println("Product ID : " + entry.getKey());
                System.out.println("Product Name : " + product.getName());
                System.out.println("Product Category : " + product.getCategory());
                System.out.println("Product Description : " + product.getDescription());
                System.out.println("==================================================================");
            }
        }
    }


}
