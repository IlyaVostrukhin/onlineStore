package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class PublicDataController {

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAllProducts() {
        log.error("App call getAllProducts method");
        return "products";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getError() {
        return "error";
    }
}
