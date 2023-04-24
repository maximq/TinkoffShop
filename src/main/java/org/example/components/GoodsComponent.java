package org.example.components;

import org.example.entity.Product;

public class GoodsComponent {

    /*public static Product addNewGoods(String name, double price) {
        for (Product product : ProductComponent.getListOfProducts()) {
            if (product.getName().equals(name)) {
                if (!(product instanceof Goods)) {
                    throw new IllegalArgumentException(
                            String.format("Услуга с таким именем '%s' уже сущетвует", name
                            )
                    );
                }
                System.out.println("Товар с таким именем уже существует, " +
                        "изменим ему цену и прибавим количество"
                );
                Goods goods = (Goods) product;
                goods.setPrice(price);
                goods.setRemainder(goods.getRemainder()+1);
                return goods;
            }
        }
        Goods newGoods = new Goods(
                ProductComponent.getListOfProducts().size()+1L,
                name,
                price
        );
        ProductComponent.addProductToList(newGoods);
        return newGoods;
    }*/
}
