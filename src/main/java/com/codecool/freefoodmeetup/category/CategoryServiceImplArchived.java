package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import com.codecool.freefoodmeetup.logger.LoggerService;
import com.codecool.freefoodmeetup.meetup.Meetup;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

@Primary
@Service
public class CategoryServiceImplArchived implements CategoryServiceInterface {
    private CategoryRepository repository;
    private CategoryValidator validator;
    private LoggerService logger;

    public CategoryServiceImplArchived(CategoryRepository repository, CategoryValidator validator, LoggerService logger) {
        this.repository = repository;
        this.validator = validator;
        this.logger = logger;
    }

    public Iterable<Category> findAll() {
        logger.logInfo("Looking for all categories...");
        logger.logInfo("All categories found");

        return this.repository.findByArchived(false);
    }

    public Category findOne(Integer id) {
        logger.logInfo("Looking for category...");
        checkExistence(id);
        checkArchivisation(id);

        logger.logInfo("Category by id: " + id +" found");

        return this.repository.findOne(id);
    }

    public Category save(Category category) {
        logger.logInfo("Creating category...");

        this.validator.checkIdIsNull(category);
        this.validator.checkNotEmptyFields(category);

        logger.logInfo("New category created");

        return this.repository.save(category);
    }

    public Category update(Category category) {
        logger.logInfo("Updating category...");

        Integer id = category.getId();
        this.validator.checkHasId(category);
        checkExistence(id);
        checkArchivisation(id);

        this.validator.checkNotEmptyFields(category);

        logger.logInfo("Category updated");

        return this.repository.save(category);
    }

    @Override
    public void delete(Integer id) {
        logger.logInfo("Archiving category...");

        checkExistence(id);
        checkArchivisation(id);

        Category category = this.repository.findOne(id);
        logger.logInfo("Archiving category of id: " + id);
        for(Meetup meetup : category.getMeetups()) {
            meetup.setArchived(true);
            logger.logInfo("Archiving meetup of id: " + meetup.getId());
        }
        category.setArchived(true);

        logger.logInfo("Archiving complete");
        this.repository.save(category);
    }

    private void checkExistence(Integer id) {
        logger.logInfo("Looking for Category of id: " + id);
        if(!this.repository.exists(id) ) {
            logger.logInfo("Looking for Category of id: " + id + " not found!");
            throw new ResourceNotFoundException("No category of id: " + id);
        }
        logger.logInfo("Looking for Category of id: " + id + " found!");
    }

    private void checkArchivisation(Integer id) {
        logger.logInfo("Checking is Category of id: "+ id + " archived...");
        if(this.repository.findByIdAndArchived(id, true) != null) {
            logger.logInfo("Category of id: " + id + " is archived");
            throw new ResourceNotFoundException("Category of id: " + id + " was archived");
        }
        logger.logInfo("Category of id: " + id + " is not archived");
    }
}
