package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.WrongValueException;

public class CategoryValidator {
    public static void checkNotEmptyFields(Category category) {
        if(category.getName() == null) {
            throw new WrongValueException("Name can not be null");
        } else if(category.getName().isEmpty()) {
            throw new WrongValueException("Name can not be empty");
        }
    }

    public static void checkHasId(Category category) {
        if(category.getId() != null) {
            throw new WrongValueException("Id should be given");
        }
    }

    public static void checkIdIsNull(Category category) {
        if(category.getId() == null) {
            throw new WrongValueException("Id should not be given");
        }
    }
}
