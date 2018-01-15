package com.codecool.freefoodmeetup.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return new ArrayList<>();
    }
}
