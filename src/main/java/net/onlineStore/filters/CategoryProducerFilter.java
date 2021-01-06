package net.onlineStore.filters;

import net.onlineStore.services.ProductService;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CategoryProducerFilter extends AbstractFilter {

    private final ProductService productService;

    public CategoryProducerFilter(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setAttribute("category_list", productService.findAllCategories());
        request.setAttribute("producer_list", productService.findAllProducers());
        chain.doFilter(request, response);
    }
}
