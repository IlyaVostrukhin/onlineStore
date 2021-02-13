package net.onlineStore.services.impl;

import net.onlineStore.entities.Category;
import net.onlineStore.entities.Producer;
import net.onlineStore.entities.Product;
import net.onlineStore.exception.InternalServerErrorException;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProducerRepository producerRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProducerRepository producerRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.producerRepository = producerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllProductsByAmountGreaterThanZero(Pageable pageable) {
        return productRepository.findAllByAmountGreaterThan(0, pageable);
    }

    @Override
    public Page<Product> findAllByAmountGreaterThanAndCategory(Category category, Pageable pageable) {
        return productRepository.findAllByAmountGreaterThanAndCategory(0, category, pageable);
    }

    @Override
    public Page<Product> findAllBySearchFormByAmountGreaterThanZero(SearchForm searchForm, Pageable pageable) {
        List<Integer> producers = searchForm.getProducers();
        List<Integer> categories = searchForm.getCategories();

        if (producers.isEmpty()) {
            producers = producerRepository.findAll().stream().map(Producer::getId).collect(Collectors.toList());
        }

        if (categories.isEmpty()) {
            categories = categoryRepository.findAll().stream().map(Category::getId).collect(Collectors.toList());
        }

        return productRepository.findAllBySearchFormByAmountGreaterThanZero(searchForm.getQuery(), categories, producers, pageable);
    }

    @Override
    public Page<Product> searchProductsByIdOrNameOrDescOrCategoryOrProducer(String query, Pageable pageable) {
        return productRepository.searchProductsByIdOrNameOrDescOrCategoryOrProducer(query, pageable);
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

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new InternalServerErrorException("Product not found by id=" + id);
        }
    }
}
