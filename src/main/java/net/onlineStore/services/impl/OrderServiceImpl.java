package net.onlineStore.services.impl;

import net.onlineStore.entities.Order;
import net.onlineStore.entities.OrderItem;
import net.onlineStore.entities.OrderStatus;
import net.onlineStore.exception.InternalServerErrorException;
import net.onlineStore.model.CurrentProfile;
import net.onlineStore.model.ShoppingCart;
import net.onlineStore.model.ShoppingCartItem;
import net.onlineStore.repositories.OrderItemRepository;
import net.onlineStore.repositories.OrderRepository;
import net.onlineStore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public long makeOrder(ShoppingCart shoppingCart, CurrentProfile currentProfile, String recipient) {
        if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
            throw new InternalServerErrorException("Shopping Cart is null or Empty");
        }
        Order order = new Order();
        order.setIdProfile(currentProfile.getId());
        order.setStatus(new OrderStatus(1, "Создан"));
        order.setRecipient(recipient);
        order.setCreated(new Timestamp(System.currentTimeMillis()));
        long orderId = orderRepository.save(order).getId();
        List<OrderItem> items = new ArrayList<>();

        for (ShoppingCartItem shopCartItems : shoppingCart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setIdOrder(orderId);
            orderItem.setProduct(shopCartItems.getProduct());
            orderItem.setCount(shopCartItems.getCount());
            orderItem.setPrice(shopCartItems.getProduct().getPrice());
            items.add(orderItemRepository.save(orderItem));
        }

        order.setItems(items);
        return orderRepository.save(order).getId();
    }

    @Override
    public Page<Order> findAllByProfileId(Long id, Pageable pageable) {
        return orderRepository.findAllByIdProfile(id, pageable);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
