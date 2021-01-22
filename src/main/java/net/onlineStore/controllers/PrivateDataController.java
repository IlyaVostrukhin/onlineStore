package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class PrivateDataController {

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getUserOrders() {
        return "orders";
    }
}
