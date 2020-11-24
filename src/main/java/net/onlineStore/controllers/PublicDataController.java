package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.entities.Product;
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

@Slf4j
@Controller
public class PublicDataController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAllProducts(Model model, @PageableDefault(size = 12) @SortDefault(sort = "id") Pageable pageable) {
        Page<Product> products = productService.findAllProducts(pageable);
        model.addAttribute("products", products.getContent());
        model.addAttribute("page", products);
        return "products";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getError() {
        return "error";
    }

    @RequestMapping(value = "/ajax/html/more/products", method = RequestMethod.GET)
    public String getMoreProducts(Model model, @PageableDefault(size = 12) @SortDefault(sort = "id") Pageable pageable) {
        Page<Product> products = productService.findAllProducts(pageable);
        model.addAttribute("products", products.getContent());
        return "../fragment/product-list";
    }
}
