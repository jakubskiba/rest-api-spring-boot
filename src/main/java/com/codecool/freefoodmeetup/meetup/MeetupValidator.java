package com.codecool.freefoodmeetup.meetup;

import com.codecool.freefoodmeetup.exceptions.WrongValueException;
import com.codecool.freefoodmeetup.logger.LoggerService;
import org.springframework.stereotype.Component;

@Component

public class MeetupValidator {
    private LoggerService logger;

    public MeetupValidator(LoggerService logger) {
        this.logger = logger;
    }
    public void chceckHasId(Meetup meetup) {
        logger.logInfo("Checking Meetup have id...");
        if(meetup.getId() == null) {
            logger.logError("Given category have not id");
            throw new WrongValueException("Id should be given");
        }

    }

    public void checkIdIsNull(Meetup meetup) {
        logger.logInfo("Checking Meetup donsn't have id...");
        if(meetup.getId() != null) {
            logger.logError("Given category have id");
            throw new WrongValueException("Id should not be given");
        }
    }

    public void checkNotEmptyFields(Meetup meetup) {
        logger.logInfo("Checking for category has empty fields");
        checkNameNotEmpty(meetup);
        checkDateNotNull(meetup);
        checkDescriptionNotEmpty(meetup);
        checkCategoryNotNull(meetup);
    }

    private void checkCategoryNotNull(Meetup meetup) {
        logger.logInfo("Checking Meetup has null category...");
        if(meetup.getCategory() == null) {
            logger.logError("Meetup has null category!");
            throw new WrongValueException("Category can not be null");
        }
    }

    private void checkDescriptionNotEmpty(Meetup meetup) {
        logger.logInfo("Checking Meetup has null description...");
        if(meetup.getDescription() == null) {
            logger.logError("Meetup has null description!");
            throw new WrongValueException("Description can not be null");
        } else if(meetup.getName().isEmpty()) {
            logger.logError("Meetup has empty description!");
            throw new WrongValueException("Description can not be empty");
        }
    }

    private void checkDateNotNull(Meetup meetup) {
        logger.logInfo("Checking Meetup has null date...");
        if(meetup.getDate() == null) {
            logger.logError("Meetup has null date!");
            throw new WrongValueException("Date can not be null");
        }
    }

    private void checkNameNotEmpty(Meetup meetup) {
        logger.logInfo("Checking Meetup has null name...");
        if(meetup.getName() == null) {
            logger.logError("Meetup has null name!");
            throw new WrongValueException("Name can not be null");
        } else if(meetup.getName().isEmpty()) {
            logger.logError("Meetup has empty name!");
            throw new WrongValueException("Name can not be empty");
        }
    }

}
