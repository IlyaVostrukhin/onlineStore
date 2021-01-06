package net.onlineStore.services.impl;

import net.onlineStore.entities.Category;
import net.onlineStore.repositories.CategoryRepository;
import net.onlineStore.services.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByUrl(String categoryUrl) {
        return categoryRepository.findByUrl(categoryUrl);
    }
}
