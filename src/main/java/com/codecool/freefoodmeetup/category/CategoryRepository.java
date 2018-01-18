package com.codecool.freefoodmeetup.category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    List<Category> findByArchived(Boolean archived);
    Category findByIdAndArchived(Integer id, Boolean archived);
}
