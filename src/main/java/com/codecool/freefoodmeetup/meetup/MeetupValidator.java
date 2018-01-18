package com.codecool.freefoodmeetup.meetup;

import com.codecool.freefoodmeetup.exceptions.WrongValueException;
import org.springframework.stereotype.Component;

@Component
public class MeetupValidator {
    public void chceckHasId(Meetup meetup) {
        if(meetup.getId() == null) {
            throw new WrongValueException("Id should be given");
        }
    }

    public void checkIdIsNull(Meetup meetup) {
        if(meetup.getId() != null) {
            throw new WrongValueException("Id should not be given");
        }
    }

    public void checkNotEmptyFields(Meetup meetup) {
        checkNameNotEmpty(meetup);
        checkDateNotNull(meetup);
        checkDescriptionNotEmpty(meetup);
        checkCategoryNotNull(meetup);
    }

    private void checkCategoryNotNull(Meetup meetup) {
        if(meetup.getCategory() == null) {
            throw new WrongValueException("Category can not be null");
        }
    }

    private void checkDescriptionNotEmpty(Meetup meetup) {
        if(meetup.getDescription() == null) {
            throw new WrongValueException("Description can not be null");
        } else if(meetup.getName().isEmpty()) {
            throw new WrongValueException("Description can not be empty");
        }
    }

    private void checkDateNotNull(Meetup meetup) {
        if(meetup.getDate() == null) {
            throw new WrongValueException("Date can not be null");
        }
    }

    private void checkNameNotEmpty(Meetup meetup) {
        if(meetup.getName() == null) {
            throw new WrongValueException("Name can not be null");
        } else if(meetup.getName().isEmpty()) {
            throw new WrongValueException("Name can not be empty");
        }
    }

}
