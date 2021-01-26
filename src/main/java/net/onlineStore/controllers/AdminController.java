package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.model.ShoppingCart;
import net.onlineStore.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProfileService profileService;
    private final ShoppingCart shoppingCart;

    @Autowired
    public AdminController(ProfileService profileService, ShoppingCart shoppingCart) {
        this.profileService = profileService;
        this.shoppingCart = shoppingCart;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAdminPanel(Model model) {
        model.addAttribute("allUsers", profileService.findAll());
        if (shoppingCart.getItems().isEmpty()) {
            model.addAttribute("currentShoppingCart", null);
        } else {
            model.addAttribute("currentShoppingCart", shoppingCart);
        }
        return "admin-main";
    }
}
