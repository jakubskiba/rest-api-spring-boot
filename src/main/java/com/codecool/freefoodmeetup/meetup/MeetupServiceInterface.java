package com.codecool.freefoodmeetup.meetup;

import com.codecool.freefoodmeetup.category.Category;
import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

public interface MeetupServiceInterface {
    Iterable<Meetup> findAll();
    Meetup findOne(Integer id);
    Meetup save(Meetup meetup);
    void delete(Integer id);
    Meetup update(Meetup meetup);
}
