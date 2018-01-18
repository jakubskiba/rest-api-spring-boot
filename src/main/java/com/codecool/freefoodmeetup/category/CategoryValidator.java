package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.WrongValueException;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {
    public void checkNotEmptyFields(Category category) {
        if(category.getName() == null) {
            throw new WrongValueException("Name can not be null");
        } else if(category.getName().isEmpty()) {
            throw new WrongValueException("Name can not be empty");
        }
    }

    public void checkIdIsNull(Category category) {
        if(category.getId() != null) {
            throw new WrongValueException("Id should not be given");
        }
    }

    public void checkHasId(Category category) {
        if(category.getId() == null) {
            throw new WrongValueException("Id should be given");
        }
    }
}
