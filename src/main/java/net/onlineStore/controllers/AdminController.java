package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.services.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProfileService profileService;

    public AdminController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAdminPanel(Model model) {
        model.addAttribute("allUsers", profileService.findAll());
        return "admin-main";
    }
}
