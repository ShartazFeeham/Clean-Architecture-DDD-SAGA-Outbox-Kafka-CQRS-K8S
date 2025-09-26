package cadsok.order.data.source.mapper;

import cadsok.order.data.source.entities.ProductEntity;
import cadsok.order.domain.core.entity.Product;
import commonmodule.domain.values.Money;
import commonmodule.domain.values.ProductId;

import java.math.BigDecimal;

public class ProductMapper {

    public static Product toProduct(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        Product product = new Product(new ProductId(productEntity.getId()), productEntity.getName(), new Money(new BigDecimal(productEntity.getPrice())));
        product.updateWithConfirmedNameAndPrice(productEntity.getName(), new Money(new BigDecimal(productEntity.getPrice())));
        return product;
    }

    public static ProductEntity toProductEntity(Product product) {
        if (product == null) {
            return null;
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId().getValue());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice().getAmount().toString());
        return productEntity;
    }
}
