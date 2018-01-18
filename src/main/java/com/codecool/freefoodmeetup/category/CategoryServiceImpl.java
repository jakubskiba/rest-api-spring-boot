package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import com.codecool.freefoodmeetup.meetup.Meetup;
import org.springframework.stereotype.Component;

@Component
public class CategoryServiceImpl implements CategoryServiceInterface {
    private CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    public Iterable<Category> findAll() {
        return this.repository.findByArchived(false);
    }

    public Category findOne(Integer id) {
        checkExistence(id);
        return this.repository.findOne(id);
    }

    public Category save(Category category) {
        CategoryValidator.checkIdIsNull(category);
        CategoryValidator.checkNotEmptyFields(category);
        return this.repository.save(category);
    }

    public Category update(Category category) {
        Integer id = category.getId();
        CategoryValidator.checkHasId(category);
        checkExistence(id);

        CategoryValidator.checkNotEmptyFields(category);
        return this.repository.save(category);
    }

    @Override
    public void delete(Integer id) {
        checkExistence(id);
        Category category = this.repository.findOne(id);
        for(Meetup meetup : category.getMeetups()) {
            meetup.setArchived(true);
        }
        category.setArchived(true);
        this.repository.save(category);
    }

    private void checkExistence(Integer id) {
        if(!this.repository.exists(id) || this.repository.findByIdAndArchived(id, true) != null) {
            throw new ResourceNotFoundException("No category of id: " + id);
        }
    }
}
