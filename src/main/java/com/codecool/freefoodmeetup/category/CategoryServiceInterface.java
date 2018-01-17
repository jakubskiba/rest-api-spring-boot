package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public interface CategoryServiceInterface {
    Iterable<Category> findAll();
    Category findOne(Integer id) throws ResourceNotFoundException;
    void save(Category category);
}
