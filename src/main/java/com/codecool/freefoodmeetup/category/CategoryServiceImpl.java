package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CategoryServiceImpl implements CategoryServiceInterface {
    private CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    public Iterable<Category> findAll() {
        return this.repository.findAll();
    }

    public Category findOne(Integer id) throws ResourceNotFoundException {
        Category category = this.repository.findOne(id);
        if(category != null) {
            return category;
        } else {
            throw new ResourceNotFoundException("No category of id: " + id);
        }
    }

    public void save(Category category) {
        this.repository.save(category);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if(this.repository.exists(id)){
            this.repository.delete(id);
        } else {
            throw new ResourceNotFoundException("No category of id: " + id);
        }
    }
}
