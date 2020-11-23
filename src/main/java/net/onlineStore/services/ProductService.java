package net.onlineStore.services;

import net.onlineStore.entities.Category;
import net.onlineStore.entities.Producer;
import net.onlineStore.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> findAllProducts(Pageable pageable);

    List<Producer> findAllProducers();

    List<Category> findAllCategories();
}
