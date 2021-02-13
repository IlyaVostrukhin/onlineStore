package net.onlineStore.services;

import net.onlineStore.entities.Category;
import net.onlineStore.entities.Producer;
import net.onlineStore.entities.Product;
import net.onlineStore.form.SearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> findAllProducts(Pageable pageable);

    Page<Product> findAllProductsByAmountGreaterThanZero(Pageable pageable);

    Page<Product> findAllByAmountGreaterThanAndCategory(Category category, Pageable pageable);

    Page<Product> findAllBySearchFormByAmountGreaterThanZero(SearchForm searchForm, Pageable pageable);

    Page<Product> searchProductsByIdOrNameOrDescOrCategoryOrProducer(String query, Pageable pageable);

    List<Producer> findAllProducers();

    List<Category> findAllCategories();

    Product findById(Long id);
}
