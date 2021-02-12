package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.Constants;
import net.onlineStore.entities.Product;
import net.onlineStore.model.ShoppingCart;
import net.onlineStore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final ProductService productService;
    private final ShoppingCart shoppingCart;

    @Autowired
    public ManagerController(ProductService productService, ShoppingCart shoppingCart) {
        this.productService = productService;
        this.shoppingCart = shoppingCart;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showManagerPanel(Model model) {
        updateShoppingCart(model);
        return "manager/manager";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String showProducts(Model model,
                               @PageableDefault(size = Constants.MAX_PRODUCTS_PER_HTML_PAGE)
                               @SortDefault(sort = "id") Pageable pageable) {
        Page<Product> products = productService.findAllProducts(pageable);
        model.addAttribute("allProducts", products.getContent());
        model.addAttribute("page", products);
        updateShoppingCart(model);
        return "manager/products";
    }

    @RequestMapping(value = "/ajax/html/more/products", method = RequestMethod.GET)
    public String showMoreProducts(
            Model model,
            @PageableDefault(size = Constants.MAX_PRODUCTS_PER_HTML_PAGE)
            @SortDefault(sort = "id") Pageable pageable
    ) {
        Page<Product> products = productService.findAllProducts(pageable);
        model.addAttribute("allProducts", products.getContent());
        return "../fragment/products-tbody";
    }

    @RequestMapping(value = "/search-products", method = RequestMethod.GET)
    public String searchProducts(@RequestParam String query,
                              Model model,
                              @PageableDefault()
                              @SortDefault(sort = "id") Pageable pageable) {
        if (query.isEmpty()) {
            return "redirect:/manager/products";
        }
        Page<Product> products = productService.searchProductsByIdOrNameOrDescOrCategoryOrProducer(query, pageable);
        model.addAttribute("query", query);
        model.addAttribute("allProducts", products.getContent());
        model.addAttribute("page", products);
        updateShoppingCart(model);
        return "manager/products";
    }

    @RequestMapping(value = "/ajax/html/more/search-products", method = RequestMethod.GET)
    public String searchMoreProducts(
            @RequestParam String query,
            Model model,
            @PageableDefault()
            @SortDefault(sort = "id") Pageable pageable
    ) {
        Page<Product> products = productService.searchProductsByIdOrNameOrDescOrCategoryOrProducer(query, pageable);
        model.addAttribute("allProducts", products.getContent());
        return "../fragment/products-tbody";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String addProducts(Model model) {
        updateShoppingCart(model);
        return "manager/add-product";
    }

    private void updateShoppingCart(Model model) {
        if (shoppingCart.getItems().isEmpty()) {
            model.addAttribute("currentShoppingCart", null);
        } else {
            model.addAttribute("currentShoppingCart", shoppingCart);
        }
    }
}
