package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    private CategoryServiceInterface service;

    public CategoryController(CategoryServiceInterface service) {
        this.service = service;
    }

    @GetMapping("")
    public Iterable<Category> index() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public Category show(@PathVariable Integer id) throws ResourceNotFoundException {
        return this.service.findOne(id);
    }

    @PostMapping("")
    public Category create(@RequestBody Category category) {
        this.service.save(category);
        return category;
    }
}
