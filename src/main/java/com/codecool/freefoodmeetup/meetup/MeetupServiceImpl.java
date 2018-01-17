package com.codecool.freefoodmeetup.meetup;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MeetupServiceImpl implements MeetupServiceInterface {
    private MeetupRepository repository;

    public MeetupServiceImpl (MeetupRepository repository) {
        this.repository = repository;
    }

    public Iterable<Meetup> findAll() {
        return this.repository.findAll();
    }

    public Meetup update(Meetup meetup) {
        return null;
    }

    public Meetup findOne(Integer id) {
        return null;
    }

    public Meetup save(Meetup meetup) {
        return null;
    }

    public void delete(Integer id) {

    }
}
