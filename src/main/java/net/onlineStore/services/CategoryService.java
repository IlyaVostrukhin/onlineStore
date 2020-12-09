package net.onlineStore.services;

import net.onlineStore.entities.Category;

public interface CategoryService {
    Category findCategoryByUrl(String categoryUrl);
}
