package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.entities.Order;
import net.onlineStore.model.ShoppingCart;
import net.onlineStore.services.OrderService;
import net.onlineStore.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class PrivateDataController {

    private final OrderService orderService;
    private final ShoppingCart shoppingCart;
    private static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";

    @Autowired
    public PrivateDataController(OrderService orderService, ShoppingCart shoppingCart) {
        this.orderService = orderService;
        this.shoppingCart = shoppingCart;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String getOrder(@RequestParam Long id,
                           Model model,
                           HttpServletRequest request
    ) {
        String message = (String) request.getSession().getAttribute(CURRENT_MESSAGE);
        request.getSession().removeAttribute(CURRENT_MESSAGE);
        request.setAttribute(CURRENT_MESSAGE, message);
        updateShoppingCart(model);
        model.addAttribute("order", orderService.findById(id));
        return "order";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout(
            Model model
    ) {
        model.addAttribute("orderForm", new Order());
        updateShoppingCart(model);
        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String createOrder(
            HttpServletRequest request,
            @ModelAttribute("orderForm") Order orderForm
    ) {
        long id = orderService.makeOrder(shoppingCart, SecurityUtils.getCurrentProfile(), orderForm.getRecipient());
        shoppingCart.clear();
        request.getSession().setAttribute(CURRENT_MESSAGE, "Заказ успешно создан. Пожалуйста, ожидайте звонка от нашего менеджера.");
        return "redirect:/order?id=" + id;
    }

    @RequestMapping(value = "/cancel-order", method = RequestMethod.GET)
    public String cancelOrder(
            @RequestParam Long id,
            Model model
    ) {
        orderService.cancelOrder(id);
        updateShoppingCart(model);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getUserOrders(Model model,
                                @PageableDefault()
                                @SortDefault(sort = "id") Pageable pageable
    ) {
        Page<Order> orders = orderService.findAllByProfileId(SecurityUtils.getCurrentProfileId(), pageable);
        updateShoppingCart(model);
        model.addAttribute("orders", orders.getContent());
        model.addAttribute("page", orders);
        return "orders";
    }

    @RequestMapping(value = "/ajax/html/more/orders", method = RequestMethod.GET)
    public String getMoreUserOrders(Model model,
                                    @PageableDefault()
                                    @SortDefault(sort = "id") Pageable pageable
    ) {
        Page<Order> orders = orderService.findAllByProfileId(SecurityUtils.getCurrentProfileId(), pageable);
        model.addAttribute("orders", orders.getContent());
        return "../fragment/orders-tbody";
    }

    private void updateShoppingCart(Model model) {
        if (shoppingCart.getItems().isEmpty()) {
            model.addAttribute("currentShoppingCart", null);
        } else {
            model.addAttribute("currentShoppingCart", shoppingCart);
        }
    }
}
