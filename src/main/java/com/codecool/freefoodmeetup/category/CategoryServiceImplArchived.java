package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import com.codecool.freefoodmeetup.meetup.Meetup;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class CategoryServiceImplArchived implements CategoryServiceInterface {
    private CategoryRepository repository;
    private CategoryValidator validator;

    public CategoryServiceImplArchived(CategoryRepository repository, CategoryValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Iterable<Category> findAll() {
        return this.repository.findByArchived(false);
    }

    public Category findOne(Integer id) {
        checkExistence(id);
        checkArchivisation(id);
        return this.repository.findOne(id);
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
        checkArchivisation(id);

        this.validator.checkNotEmptyFields(category);
        return this.repository.save(category);
    }

    @Override
    public void delete(Integer id) {
        checkExistence(id);
        checkArchivisation(id);
        Category category = this.repository.findOne(id);
        for(Meetup meetup : category.getMeetups()) {
            meetup.setArchived(true);
        }
        category.setArchived(true);
        this.repository.save(category);
    }

    private void checkExistence(Integer id) {
        if(!this.repository.exists(id) ) {
            throw new ResourceNotFoundException("No category of id: " + id);
        }
    }

    private void checkArchivisation(Integer id) {
        if(this.repository.findByIdAndArchived(id, true) != null) {
            throw new ResourceNotFoundException("Category of id: " + id + " was archived");
        }
    }
}
