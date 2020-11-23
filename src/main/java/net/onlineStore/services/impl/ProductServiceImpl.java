package net.onlineStore.services.impl;

import net.onlineStore.entities.Category;
import net.onlineStore.entities.Producer;
import net.onlineStore.entities.Product;
import net.onlineStore.repositories.CategoryRepository;
import net.onlineStore.repositories.ProducerRepository;
import net.onlineStore.repositories.ProductRepository;
import net.onlineStore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Producer> findAllProducers() {
        return producerRepository.findAll();
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
