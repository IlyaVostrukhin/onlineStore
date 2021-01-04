package net.onlineStore.services.impl;

import net.onlineStore.entities.Category;
import net.onlineStore.entities.Producer;
import net.onlineStore.entities.Product;
import net.onlineStore.form.SearchForm;
import net.onlineStore.repositories.CategoryRepository;
import net.onlineStore.repositories.ProducerRepository;
import net.onlineStore.repositories.ProductRepository;
import net.onlineStore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllByCategory(Category category, Pageable pageable) {
        return productRepository.findAllByCategory(category, pageable);
    }

    @Override
    public Page<Product> findAllBySearchForm(SearchForm searchForm, Pageable pageable) {
        List<Integer> producers = searchForm.getProducers();
        List<Integer> categories = searchForm.getCategories();

        if (producers.isEmpty()) {
            producers = producerRepository.findAll().stream().map(Producer::getId).collect(Collectors.toList());
        }

        if (categories.isEmpty()) {
            categories = categoryRepository.findAll().stream().map(Category::getId).collect(Collectors.toList());
        }

        return productRepository.findAllBySearchForm(searchForm.getQuery(), categories, producers, pageable);
    }

    @Override
    public List<Producer> findAllProducers() {
        List<Producer> producers = producerRepository.findAll();
        producers.sort(Comparator.comparing(Producer::getName));
        return producers;
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        categories.sort(Comparator.comparing(Category::getName));
        return categories;
    }
}
