package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.entities.Category;
import net.onlineStore.entities.Product;
import net.onlineStore.services.CategoryService;
import net.onlineStore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class PublicDataController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAllProducts(
            Model model,
            @PageableDefault(size = 12) @SortDefault(sort = "id") Pageable pageable) {
        Page<Product> products = productService.findAllProducts(pageable);
        model.addAttribute("products", products.getContent());
        model.addAttribute("page", products);
        return "products";
    }

    @RequestMapping(value = "/ajax/html/more/products", method = RequestMethod.GET)
    public String getMoreProducts(
            Model model,
            @PageableDefault(size = 12) @SortDefault(sort = "id") Pageable pageable) {
        Page<Product> products = productService.findAllProducts(pageable);
        model.addAttribute("products", products.getContent());
        return "../fragment/product-list";
    }

    @RequestMapping(value = "/products/{categoryName}", method = RequestMethod.GET)
    public String getProductsByCategory(
            @PathVariable String categoryName,
            Model model,
            @PageableDefault(size = 12) @SortDefault(sort = "id") Pageable pageable) {
        Category category = categoryService.findCategoryByUrl("/" + categoryName);
        Page<Product> products = productService.findAllByCategory(category, pageable);
        model.addAttribute("products", products.getContent());
        model.addAttribute("page", products);
        model.addAttribute("selectedCategoryUrl", "/" + categoryName);
        return "products";
    }

    @RequestMapping(value = "/ajax/html/more/products/{categoryName}", method = RequestMethod.GET)
    public String getProductsByCategoryMore(
            @PathVariable String categoryName,
            Model model,
            @PageableDefault(size = 12) @SortDefault(sort = "id") Pageable pageable) {
        Category category = categoryService.findCategoryByUrl("/" + categoryName);
        Page<Product> products = productService.findAllByCategory(category, pageable);
        model.addAttribute("products", products.getContent());
        return "../fragment/product-list";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getError() {
        return "error";
    }
}
