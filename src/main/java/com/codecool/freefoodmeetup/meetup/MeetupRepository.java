package com.codecool.freefoodmeetup.meetup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetupRepository extends CrudRepository<Meetup, Integer> {
    List<Meetup> findByArchived(Boolean archived);
    Meetup findByIdAndArchived(Integer id, Boolean archived);
}
