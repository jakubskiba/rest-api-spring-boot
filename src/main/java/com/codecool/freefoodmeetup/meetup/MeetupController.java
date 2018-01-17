package com.codecool.freefoodmeetup.meetup;

import com.codecool.freefoodmeetup.category.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/meetups")
public class MeetupController {
    private MeetupServiceInterface service;

    public MeetupController(MeetupServiceInterface service) {
        this.service = service;
    }

    @GetMapping("")
    public Iterable<Meetup> index() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public Meetup show(@PathVariable Integer id) {
        return this.service.findOne(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Meetup create(@RequestBody Meetup meetup) {
        return this.service.save(meetup);
    }

    @PutMapping("")
    public Meetup update(@RequestBody Meetup meetup) {
        this.service.update(meetup);
        return meetup;
    }

}
