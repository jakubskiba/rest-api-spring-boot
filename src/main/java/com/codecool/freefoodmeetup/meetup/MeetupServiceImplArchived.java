package com.codecool.freefoodmeetup.meetup;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class MeetupServiceImplArchived implements MeetupServiceInterface {
    private MeetupRepository repository;
    private MeetupValidator validator;

    public MeetupServiceImplArchived(MeetupRepository repository, MeetupValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Iterable<Meetup> findAll() {
        return this.repository.findByArchived(false);
    }

    public Meetup findOne(Integer id) {
        checkExistence(id);
        checkArchivisation(id);
        return this.repository.findOne(id);
    }

    public Meetup save(Meetup meetup) {
        this.validator.checkIdIsNull(meetup);
        this.validator.checkNotEmptyFields(meetup);
        return this.repository.save(meetup);
    }

    public Meetup update(Meetup meetup) {
        Integer id = meetup.getId();
        this.validator.chceckHasId(meetup);
        checkExistence(id);
        checkArchivisation(id);

        return this.repository.save(meetup);
    }

    public void delete(Integer id) {
        checkExistence(id);
        checkArchivisation(id);
        Meetup meetup = this.repository.findOne(id);
        meetup.setArchived(true);
        this.repository.save(meetup);
    }

    private void checkExistence(Integer id) {
        if(!this.repository.exists(id)) {
            throw new ResourceNotFoundException("No meetup of id: " + id);
        }
    }

    private void checkArchivisation(Integer id) {
        if(this.repository.findByIdAndArchived(id, true) != null) {
            throw new ResourceNotFoundException("Meetup of id: " + id + " was archived");
        }
    }
}
