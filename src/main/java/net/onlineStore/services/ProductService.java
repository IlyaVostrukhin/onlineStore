package net.onlineStore.services;

import net.onlineStore.entities.Category;
import net.onlineStore.entities.Producer;
import net.onlineStore.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    List<Producer> findAllProducers();

    List<Category> findAllCategories();
}
