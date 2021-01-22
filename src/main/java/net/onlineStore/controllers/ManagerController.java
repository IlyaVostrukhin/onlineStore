package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class ManagerController {
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String showAdminPanel() {
        return "manager";
    }
}
