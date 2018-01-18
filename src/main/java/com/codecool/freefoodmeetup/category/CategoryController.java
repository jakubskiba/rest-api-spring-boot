package com.codecool.freefoodmeetup.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("")
    public Category update(@RequestBody Category category) {
        this.service.update(category);
        return category;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.service.delete(id);
    }
}
