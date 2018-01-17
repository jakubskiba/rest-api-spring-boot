package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import com.codecool.freefoodmeetup.exceptions.WrongValueException;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeParseException;

@Component
public class CategoryServiceImpl implements CategoryServiceInterface {
    private CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    public Iterable<Category> findAll() {
        return this.repository.findAll();
    }

    public Category findOne(Integer id) {
        Category category = this.repository.findOne(id);
        checkExistence(id);
        return category;
    }

    public Category save(Category category) {
        CategoryValidator.checkHasId(category);
        CategoryValidator.checkNotEmptyFields(category);
        return this.repository.save(category);
    }

    public Category update(Category category) {
        Integer id = category.getId();
        CategoryValidator.checkIdIsNull(category);
        checkExistence(id);

        CategoryValidator.checkNotEmptyFields(category);
        return this.repository.save(category);
    }

    @Override
    public void delete(Integer id) {
        checkExistence(id);
        this.repository.delete(id);
    }

    private void checkExistence(Integer id) {
        if(!this.repository.exists(id)) {
            throw new ResourceNotFoundException("No category of id: " + id);
        }
    }
}
