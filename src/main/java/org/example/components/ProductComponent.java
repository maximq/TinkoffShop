package org.example.components;

import org.example.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ProductComponent {

    private static List<Product> listOfProducts = new ArrayList();

    public static List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public static void addProductToList(Product product) {
        listOfProducts.add(product);
    }

    public static Product getProductByName(String name) {
        for (Product listOfProduct : listOfProducts) {
            if (Objects.equals(listOfProduct.getName(), name)) {
                return listOfProduct;
            }
        }
        throw new NoSuchElementException(
                String.format("Продукта с именем '%s' нет!", name
                )
        );
    }
}
