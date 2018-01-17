package com.codecool.freefoodmeetup.meetup;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MeetupServiceImpl implements MeetupServiceInterface {
    private MeetupRepository repository;

    public MeetupServiceImpl (MeetupRepository repository) {
        this.repository = repository;
    }

    public Iterable<Meetup> findAll() {
        return this.repository.findAll();
    }

    public Meetup findOne(Integer id) {
        checkExistence(id);
        return this.repository.findOne(id);
    }

    public Meetup save(Meetup meetup) {
        MeetupValidator.checkIdIsNull(meetup);
        MeetupValidator.checkNotEmptyFields(meetup);
        return this.repository.save(meetup);
    }

    public Meetup update(Meetup meetup) {
        Integer id = meetup.getId();
        MeetupValidator.chceckHasId(meetup);
        checkExistence(id);

        return this.repository.save(meetup);
    }

    public void delete(Integer id) {

    }

    private void checkExistence(Integer id) {
        if(!this.repository.exists(id)) {
            throw new ResourceNotFoundException("No meetup of id: " + id);
        }
    }
}
