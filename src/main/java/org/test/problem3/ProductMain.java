package org.test.problem3;

import java.util.Scanner;

public class ProductMain {
    public static void main(String[] args) {
        ProductConfiguration productConfiguration = new ProductConfiguration();
        //adding the product
        productConfiguration.addProduct("1", "Macbook", "Laptop", "Used for development of software");
        productConfiguration.addProduct("2", "Hp-Laptop", "Laptop", "Used for development of software");
        productConfiguration.addProduct("3", "Iphone15", "Mobile", "Used for phone call and playing games");
        productConfiguration.addProduct("4", "Iphone15pro", "Mobile", "Used for phone call and playing games");
        productConfiguration.addProduct("5", "Maggie", "Food Items", "Instant Food items ");
        productConfiguration.addProduct("6", "Noodles", "Food Items", "Instant Food items");
        productConfiguration.addProduct("7", "Rice", "Grocery", "Grocery - items");
        productConfiguration.addProduct("8", "Sugar", "Grocery", "Grocery - items");
        productConfiguration.addProduct("9", "Oil", "Grocery", "Grocery - items");
        productConfiguration.addProduct("10", "Sony Alpha 1", "Camera", "Used for taking photos and video");
        productConfiguration.addProduct("11", "Canon 500D", "Camera", "Used for taking photos and video");
        productConfiguration.addProduct("12", "LG-Refrigerator", "Electronics", "Electronics items for home");
        productConfiguration.addProduct("13", "LG-Washing Machine", "Electronics", "Used for washing clothes");
        productConfiguration.addProduct("14", "Sony Bravia TV", "Electronics", "Television to watch movie and shows");
        productConfiguration.addProduct("15", "Oneplus TV", "Electronics", "Television to watch movie and shows");
        productConfiguration.addProduct("16", "Ps-5", "Gaming Console", "Playing video games");
        productConfiguration.addProduct("17", "Asus Ally", "Gaming Console", "Playing video games");
        productConfiguration.addProduct("18", "Samsung s23", "Mobile", "Used for phone call and playing games");
        productConfiguration.addProduct("19", "Wheat", "Grocery", "Grocery - items");
        productConfiguration.addProduct("20", "Box", "Household needs", "Daily purpose house needs");

        //searching the product
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the keyword to search items");
        String value = sc.next();
        productConfiguration.searchProduct(value);
    }
}
