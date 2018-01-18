package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImplDelete implements CategoryServiceInterface {
    private CategoryRepository repository;
    private CategoryValidator validator;

    public CategoryServiceImplDelete(CategoryRepository repository, CategoryValidator validator) {
        this.repository = repository;
        this.validator = validator;
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
        this.validator.checkIdIsNull(category);
        this.validator.checkNotEmptyFields(category);
        return this.repository.save(category);
    }

    public Category update(Category category) {
        Integer id = category.getId();
        this.validator.checkHasId(category);
        checkExistence(id);

        this.validator.checkNotEmptyFields(category);
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