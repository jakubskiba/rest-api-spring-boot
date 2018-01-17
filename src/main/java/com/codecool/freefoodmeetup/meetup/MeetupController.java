package com.codecool.freefoodmeetup.meetup;

import com.codecool.freefoodmeetup.category.Category;
import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverException;
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
        return this.service.fingdAll();
    }

}
