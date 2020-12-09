package net.onlineStore.services.impl;

import net.onlineStore.entities.Category;
import net.onlineStore.repositories.CategoryRepository;
import net.onlineStore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findCategoryByUrl(String categoryUrl) {
        return categoryRepository.findByUrl(categoryUrl);
    }
}
