package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import com.codecool.freefoodmeetup.exceptions.WrongValueException;
import org.springframework.stereotype.Component;

public interface CategoryServiceInterface {
    Iterable<Category> findAll();
    Category findOne(Integer id);
    Category save(Category category);
    void delete(Integer id);
    Category update(Category category);
}
