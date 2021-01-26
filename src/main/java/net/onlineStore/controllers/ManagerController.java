package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class ManagerController {

    private final ShoppingCart shoppingCart;

    @Autowired
    public ManagerController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String showAdminPanel(Model model) {
        if (shoppingCart.getItems().isEmpty()) {
            model.addAttribute("currentShoppingCart", null);
        } else {
            model.addAttribute("currentShoppingCart", shoppingCart);
        }
        return "manager";
    }
}
