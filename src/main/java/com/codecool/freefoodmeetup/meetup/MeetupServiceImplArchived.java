package com.codecool.freefoodmeetup.meetup;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import com.codecool.freefoodmeetup.logger.LoggerService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class MeetupServiceImplArchived implements MeetupServiceInterface {
    private MeetupRepository repository;
    private MeetupValidator validator;
    private LoggerService logger;

    public MeetupServiceImplArchived(MeetupRepository repository, MeetupValidator validator, LoggerService logger) {
        this.repository = repository;
        this.validator = validator;
        this.logger = logger;
    }

    public Iterable<Meetup> findAll() {
        logger.logInfo("Looking for all meetups...");
        logger.logInfo("All meetups found");

        return this.repository.findByArchived(false);
    }

    public Meetup findOne(Integer id) {
        logger.logInfo("Looking for meetup of id: " + id);
        checkExistence(id);
        checkArchivisation(id);

        logger.logInfo("Meetup by id: " + id +" found");
        return this.repository.findOne(id);
    }

    public Meetup save(Meetup meetup) {
        logger.logInfo("Creating meetup...");

        this.validator.checkIdIsNull(meetup);
        this.validator.checkNotEmptyFields(meetup);

        logger.logInfo("Meetup created");
        return this.repository.save(meetup);
    }

    public Meetup update(Meetup meetup) {
        logger.logInfo("Update meetup...");
        Integer id = meetup.getId();
        this.validator.chceckHasId(meetup);
        checkExistence(id);
        checkArchivisation(id);

        logger.logInfo("Meetup updated");

        return this.repository.save(meetup);
    }

    public void delete(Integer id) {
        logger.logInfo("Archiving meetup of id: " + id);
        checkExistence(id);
        checkArchivisation(id);
        Meetup meetup = this.repository.findOne(id);
        meetup.setArchived(true);

        logger.logInfo("Meetup of id: "+ id + " archived");

        this.repository.save(meetup);
    }

    private void checkExistence(Integer id) {
        logger.logInfo("Looking for Meetup of id: " + id);
        if(!this.repository.exists(id)) {
            logger.logInfo("Looking for Meetup of id: " + id + " not found!");
            throw new ResourceNotFoundException("No meetup of id: " + id);
        }
        logger.logInfo("Looking for Meetup of id: " + id + " found!");
    }

    private void checkArchivisation(Integer id) {
        logger.logInfo("Looking for meetup of id: " + id + " is archived...");
        if(this.repository.findByIdAndArchived(id, true) != null) {
            logger.logInfo("Meetup of id: " + id + " is archived");
            throw new ResourceNotFoundException("Meetup of id: " + id + " was archived");
        }
        logger.logInfo("Category of id: " + id + " is not archived");
    }
}
