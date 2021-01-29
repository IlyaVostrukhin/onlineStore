package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.entities.Profile;
import net.onlineStore.entities.Role;
import net.onlineStore.model.ShoppingCart;
import net.onlineStore.services.ProfileService;
import net.onlineStore.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProfileService profileService;
    private final RoleService roleService;
    private final ShoppingCart shoppingCart;

    @Autowired
    public AdminController(ProfileService profileService, RoleService roleService, ShoppingCart shoppingCart) {
        this.profileService = profileService;
        this.roleService = roleService;
        this.shoppingCart = shoppingCart;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAdminPanel(Model model) {
        if (shoppingCart.getItems().isEmpty()) {
            model.addAttribute("currentShoppingCart", null);
        } else {
            model.addAttribute("currentShoppingCart", shoppingCart);
        }
        return "admin/admin";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model,
                           @PageableDefault(size = 2)
                           @SortDefault(sort = "id") Pageable pageable) {
        Page<Profile> profiles = profileService.findAll(pageable);
        model.addAttribute("allUsers", profiles.getContent());
        model.addAttribute("page", profiles);
        updateShoppingCart(model);
        return "admin/users";
    }

    @RequestMapping(value = "/ajax/html/more/users", method = RequestMethod.GET)
    public String getMoreUsers(Model model,
                               @PageableDefault(size = 2)
                               @SortDefault(sort = "id") Pageable pageable) {
        Page<Profile> profiles = profileService.findAll(pageable);
        model.addAttribute("allUsers", profiles.getContent());
        return "../fragment/users-tbody";
    }

    @RequestMapping(value = "/search-users", method = RequestMethod.GET)
    public String searchUsers(@RequestParam String query,
                              Model model,
                              @PageableDefault(size = 2)
                              @SortDefault(sort = "id") Pageable pageable) {
        Page<Profile> profiles = profileService.searchUsers(query, pageable);
        model.addAttribute("query", query);
        model.addAttribute("allUsers", profiles.getContent());
        model.addAttribute("page", profiles);
        updateShoppingCart(model);
        return "admin/users";
    }

    @RequestMapping(value = "/ajax/html/more/search-users", method = RequestMethod.GET)
    public String searchMoreUsers(@RequestParam String query,
                                  Model model,
                                  @PageableDefault(size = 2)
                                  @SortDefault(sort = "id") Pageable pageable) {
        Page<Profile> profiles = profileService.searchUsers(query, pageable);
        model.addAttribute("allUsers", profiles.getContent());
        return "../fragment/users-tbody";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(@RequestParam Long id,
                          Model model) {
        Profile profile = profileService.findById(id);
        profile.setPassword("");
        model.addAttribute("user", profile);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("profileForm", new Profile());
        updateShoppingCart(model);
        return "admin/user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String editUser(@RequestParam Long id,
                           @ModelAttribute("profileForm") @Valid Profile profileForm,
                           @RequestParam(required = false) String[] selectedRoles,
                           BindingResult bindingResult,
                           Model model) {
        Profile profile = profileService.findById(id);
        if (bindingResult.hasErrors()) {
            return "admin/user";
        }
        if (!profileForm.getPassword().equals(profileForm.getConfirmPassword())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "admin/user";
        }
        if (!profile.getLogin().equals(profileForm.getLogin()) && profileService.findByLogin(profileForm.getLogin()) != null) {
            model.addAttribute("loginError",
                    "Пользователь с таким логином уже существует");
            return "admin/user";
        }
        if (!profile.getEmail().equals(profileForm.getEmail()) && profileService.findByEmail(profileForm.getEmail()) != null) {
            model.addAttribute("emailError",
                    "Пользователь с таким e-mail уже существует");
            return "admin/user";
        }
        if (!profile.getPhone().equals(profileForm.getPhone()) && profileService.findByPhone(profileForm.getPhone()) != null) {
            model.addAttribute("phoneError",
                    "Пользователь с таким номером телефона уже существует");
            return "admin/user";
        }
        Set<Role> newRoles = new HashSet<>();
        for (String roleId : selectedRoles) {
            newRoles.add(roleService.findById(Long.parseLong(roleId)));
        }
        roleService.save(newRoles);
        profileForm.setRoles(newRoles);
        profileService.saveProfile(profileForm);
        return "redirect:/admin/users";
    }

    private void updateShoppingCart(Model model) {
        if (shoppingCart.getItems().isEmpty()) {
            model.addAttribute("currentShoppingCart", null);
        } else {
            model.addAttribute("currentShoppingCart", shoppingCart);
        }
    }
}
