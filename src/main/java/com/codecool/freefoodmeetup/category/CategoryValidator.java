package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.WrongValueException;
import com.codecool.freefoodmeetup.logger.LoggerService;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {
    private LoggerService logger;

    public CategoryValidator(LoggerService logger) {
        this.logger = logger;
    }

    public void checkNotEmptyFields(Category category) {
        logger.logInfo("Checking for category has empty fields");
        if(category.getName() == null) {
            logger.logError("Category has null name!");
            throw new WrongValueException("Name can not be null");
        } else if(category.getName().isEmpty()) {
            logger.logError("Category has empty name field!");
            throw new WrongValueException("Name can not be empty");
        }
    }

    public void checkIdIsNull(Category category) {
        logger.logInfo("Checking Category donsn't have id");
        if(category.getId() != null) {
            logger.logError("Given category have id");
            throw new WrongValueException("Id should not be given");
        }
    }

    public void checkHasId(Category category) {
        logger.logInfo("Checking Category have id");
        if(category.getId() == null) {
            throw new WrongValueException("Id should be given");
        }
    }
}
