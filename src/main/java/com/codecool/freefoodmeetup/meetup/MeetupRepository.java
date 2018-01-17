package com.codecool.freefoodmeetup.meetup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetupRepository extends CrudRepository<Meetup, Integer> {

}
