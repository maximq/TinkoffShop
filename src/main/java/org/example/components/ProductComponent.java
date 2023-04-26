package org.example.components;

import org.example.entity.Product;
import org.example.enums.ProductType;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ProductComponent {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getListOfProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String name) {
        var product = productRepository.findByName(name);
        if (product != null) {
            return product;
        }
        throw new NoSuchElementException(
                String.format("Продукта с именем '%s' нет!", name
                )
        );
    }

    public Product addNewGoods(String name, double price) {

        var product = productRepository.findByName(name);

        if (product == null) {
            Product productNew = new Product(name, price, ProductType.GOOD);
            productRepository.save(productNew);
            return productNew;
        }

        if (product.getProductType() == ProductType.GOOD) {
            product.setPrice(price);
            product.setRemainder(product.getRemainder() + 1);
            productRepository.save(product);
            return product;
        }
        throw new IllegalArgumentException(
                String.format("Услуга с таким именем '%s' уже сущетвует", name
                )
        );

    }

    public Product addNewServe(String name, double price) {
        var product = productRepository.findByName(name);
        if (product != null) {
            throw new IllegalArgumentException(
                    String.format("Товар с именем '%s' уже существует", name
                    )
            );
        }
        Product serve = new Product(
                name,
                price,
                ProductType.SERVE
        );
        productRepository.save(serve);
        return serve;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);

    }
}
