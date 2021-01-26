package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.entities.Profile;
import net.onlineStore.services.ProfileService;
import net.onlineStore.utils.SecurityUtils;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class AuthController {

    private final ProfileService profileService;

    public AuthController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(value = "/sign-in")
    public String signIn() {
        if (SecurityUtils.isCurrentProfileAuthenticated()) {
            return "redirect:/orders";
        }
        return "sign-in";
    }

    @RequestMapping(value = "/sign-in-failed")
    public String signInFailed(HttpSession session) {
        if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null){
            return "redirect:/sign-in";
        }
        return "sign-in";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("profileForm", new Profile());
        if (SecurityUtils.getCurrentProfile() != null) {
            return "redirect:/orders";
        }
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerNewProfile(@ModelAttribute("profileForm") @Valid Profile profileForm,
                                     BindingResult bindingResult,
                                     Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!profileForm.getPassword().equals(profileForm.getConfirmPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (profileService.findByLogin(profileForm.getLogin()) != null){
            model.addAttribute("loginError",
                    "Пользователь с таким логином уже существует");
            return "registration";
        }
        if (profileService.findByEmail(profileForm.getEmail()) != null){
            model.addAttribute("emailError",
                    "Пользователь с таким e-mail уже существует");
            return "registration";
        }
        if (profileService.findByPhone(profileForm.getPhone()) != null){
            model.addAttribute("phoneError",
                    "Пользователь с таким номером телефона уже существует");
            return "registration";
        }
        SecurityUtils.authenticate(profileService.saveProfile(profileForm));
        return "redirect:/products";
    }
}
