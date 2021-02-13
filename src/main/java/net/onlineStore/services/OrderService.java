package net.onlineStore.services;

import net.onlineStore.entities.Order;
import net.onlineStore.model.CurrentProfile;
import net.onlineStore.model.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    long makeOrder(ShoppingCart shoppingCart, CurrentProfile currentProfile, String recipient);

    void cancelOrder(long id);

    Page<Order> findAllByProfileId(Long id, Pageable pageable);

    Order findById(Long id);
}
