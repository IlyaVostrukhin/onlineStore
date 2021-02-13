package net.onlineStore.controllers;

import lombok.extern.slf4j.Slf4j;
import net.onlineStore.Constants;
import net.onlineStore.entities.Category;
import net.onlineStore.entities.Product;
import net.onlineStore.form.ProductForm;
import net.onlineStore.form.SearchForm;
import net.onlineStore.model.ShoppingCart;
import net.onlineStore.services.CategoryService;
import net.onlineStore.services.ProductService;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class PublicDataController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ShoppingCart shoppingCart;

    public PublicDataController(ProductService productService, CategoryService categoryService, ShoppingCart shoppingCart) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.shoppingCart = shoppingCart;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAllProducts(
            Model model,
            @PageableDefault(size = Constants.MAX_PRODUCTS_PER_HTML_PAGE)
            @SortDefault(sort = "id") Pageable pageable
    ) {
        Page<Product> products = productService.findAllProductsByAmountGreaterThanZero(pageable);
        model.addAttribute("products", products.getContent());
        updateShoppingCart(model);
        model.addAttribute("page", products);
        return "products";
    }

    @RequestMapping(value = "/ajax/html/more/products", method = RequestMethod.GET)
    public String getMoreProducts(
            Model model,
            @PageableDefault(size = Constants.MAX_PRODUCTS_PER_HTML_PAGE)
            @SortDefault(sort = "id") Pageable pageable
    ) {
        Page<Product> products = productService.findAllProductsByAmountGreaterThanZero(pageable);
        model.addAttribute("products", products.getContent());
        return "../fragment/product-list";
    }

    @RequestMapping(value = "/products/{categoryName}", method = RequestMethod.GET)
    public String getProductsByCategory(
            @PathVariable String categoryName,
            Model model,
            @PageableDefault(size = Constants.MAX_PRODUCTS_PER_HTML_PAGE)
            @SortDefault(sort = "id") Pageable pageable
    ) {
        Category category = categoryService.findCategoryByUrl("/" + categoryName);
        Page<Product> products = productService.findAllByAmountGreaterThanAndCategory(category, pageable);
        model.addAttribute("products", products.getContent());
        updateShoppingCart(model);
        model.addAttribute("page", products);
        model.addAttribute("selectedCategoryUrl", "/" + categoryName);
        return "products";
    }

    @RequestMapping(value = "/ajax/html/more/products/{categoryName}", method = RequestMethod.GET)
    public String getMoreProductsByCategory(
            @PathVariable String categoryName,
            Model model,
            @PageableDefault(size = Constants.MAX_PRODUCTS_PER_HTML_PAGE)
            @SortDefault(sort = "id") Pageable pageable
    ) {
        Category category = categoryService.findCategoryByUrl("/" + categoryName);
        Page<Product> products = productService.findAllByAmountGreaterThanAndCategory(category, pageable);
        model.addAttribute("products", products.getContent());
        return "../fragment/product-list";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getProductsBySearchResult(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String[] category,
            @RequestParam(required = false) String[] producer,
            Model model,
            @PageableDefault(size = Constants.MAX_PRODUCTS_PER_HTML_PAGE)
            @SortDefault(sort = "id") Pageable pageable
    ) {
        SearchForm searchForm = new SearchForm(query, category, producer);
        Page<Product> products = productService.findAllBySearchFormByAmountGreaterThanZero(searchForm, pageable);
        updateShoppingCart(model);
        model.addAttribute("products", products.getContent());
        model.addAttribute("page", products);
        model.addAttribute("productCount", (int) products.getTotalElements());
        model.addAttribute("searchForm", searchForm);
        return "../page/search-result";
    }

    @RequestMapping(value = "/ajax/html/more/search", method = RequestMethod.GET)
    public String getMoreProductsBySearchResult(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String[] category,
            @RequestParam(required = false) String[] producer,
            Model model,
            @PageableDefault(size = Constants.MAX_PRODUCTS_PER_HTML_PAGE)
            @SortDefault(sort = "id") Pageable pageable
    ) {
        SearchForm searchForm = new SearchForm(query, category, producer);
        Page<Product> products = productService.findAllBySearchFormByAmountGreaterThanZero(searchForm, pageable);
        model.addAttribute("products", products.getContent());
        return "../fragment/product-list";
    }

    @RequestMapping(value = "/ajax/json/product/add", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String addProductToShoppingCart(
            HttpServletRequest request
    ) {
        ProductForm productForm = new ProductForm(
                Long.parseLong(request.getParameter("idProduct")),
                Integer.parseInt(request.getParameter("count"))
        );
        Product product = productService.findById(productForm.getIdProduct());
        shoppingCart.addProduct(product, productForm.getCount());

        JSONObject cardStatistics = new JSONObject();
        cardStatistics.put("totalCount", shoppingCart.getTotalCount());
        cardStatistics.put("totalCost", shoppingCart.getTotalCost());

        return cardStatistics.toString();
    }

    @RequestMapping(value = "/ajax/json/product/remove", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String removeProductFromShoppingCart(
            HttpServletRequest request,
            Model model
    ) {
        ProductForm productForm = new ProductForm(
                Long.parseLong(request.getParameter("idProduct")),
                Integer.parseInt(request.getParameter("count"))
        );
        shoppingCart.removeProduct(productForm.getIdProduct(), productForm.getCount());

        updateShoppingCart(model);
        if (model.getAttribute("currentShoppingCart") == null) {
            shoppingCart.clear();
        }

        JSONObject cardStatistics = new JSONObject();
        cardStatistics.put("totalCount", shoppingCart.getTotalCount());
        cardStatistics.put("totalCost", shoppingCart.getTotalCost());

        return cardStatistics.toString();
    }

    @RequestMapping(value = "/shopping-cart", method = RequestMethod.GET)
    public String showShoppingCart(
            Model model
    ) {
        updateShoppingCart(model);
        if (model.getAttribute("currentShoppingCart") == null) {
            return "redirect:/products";
        }
        return "shopping-cart";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getError() {
        return "error";
    }

    private void updateShoppingCart(Model model) {
        if (shoppingCart.getItems().isEmpty()) {
            model.addAttribute("currentShoppingCart", null);
        } else {
            model.addAttribute("currentShoppingCart", shoppingCart);
        }
    }
}
