package cadsok.order.domain.core.entity;

import cadsok.common.domain.entity.BaseEntity;
import cadsok.common.domain.values.Money;
import cadsok.common.domain.values.ProductId;

public class Product extends BaseEntity<ProductId> {

    private String name;
    private Money price;

    public Product(ProductId productId, String name, Money price) {
        setId(productId);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
