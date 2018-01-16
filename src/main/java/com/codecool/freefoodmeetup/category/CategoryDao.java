package com.codecool.freefoodmeetup.category;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface CustomerDao extends JpaRepository<Customer, Integer> {

}