package com.codecool.freefoodmeetup.category;

import com.codecool.freefoodmeetup.exceptions.ResourceNotFoundException;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public Category show(@PathVariable Integer id) {
        return this.service.findOne(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Category create(@RequestBody Category category) {
        return this.service.save(category);
    }
        return category;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.service.delete(id);
    }
}
