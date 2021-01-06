package net.onlineStore.model;

import net.onlineStore.Constants;
import net.onlineStore.entities.Product;
import net.onlineStore.exception.ValidationException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 3022523882292122729L;

    private final Map<Long, ShoppingCartItem> products = new LinkedHashMap<>();
    private int totalCount = 0;

    private BigDecimal totalCost = BigDecimal.ZERO;

    public void addProduct(Product product, int count) {
        validateShoppingCartSize(product.getId());
        ShoppingCartItem shoppingCartItem = products.get(product.getId());
        if (shoppingCartItem == null) {
            validateProductCount(count);
            shoppingCartItem = new ShoppingCartItem(product, count);
            products.put(product.getId(), shoppingCartItem);
        } else {
            validateProductCount(count + shoppingCartItem.getCount());
            shoppingCartItem.setCount(count + shoppingCartItem.getCount());
        }
        refreshStatistics();
    }

    public void removeProduct(Long idProduct, int count) {
        ShoppingCartItem shoppingCartItem = products.get(idProduct);
        if (shoppingCartItem != null) {
            if (shoppingCartItem.getCount() > count) {
                shoppingCartItem.setCount(shoppingCartItem.getCount() - count);
            } else {
                products.remove(idProduct);
            }
            refreshStatistics();
        }
    }

    public Collection<ShoppingCartItem> getItems() {
        return products.values();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    private void validateProductCount(int count) {
        if (count > Constants.MAX_PRODUCT_COUNT_PER_SHOPPING_CART) {
            throw new ValidationException("Limit for product count reached: count = " + count);
        }
    }

    private void validateShoppingCartSize(long idProduct) {
        if (products.size() > Constants.MAX_PRODUCTS_PER_SHOPPING_CART ||
                (products.size() == Constants.MAX_PRODUCTS_PER_SHOPPING_CART
                        && !products.containsKey(idProduct))) {
            throw new ValidationException("Limit for ShoppingCart size reached: size = " + products.size());
        }
    }

    private void refreshStatistics() {
        totalCount = 0;
        totalCost = BigDecimal.ZERO;
        for (ShoppingCartItem shoppingCartItem : getItems()) {
            totalCount += shoppingCartItem.getCount();
            totalCost = totalCost.add(shoppingCartItem.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(shoppingCartItem.getCount())));
        }
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("ShoppingCart [products=%s, totalCount=%s, totalCost=%s]", products, totalCount, totalCost);
    }
}
