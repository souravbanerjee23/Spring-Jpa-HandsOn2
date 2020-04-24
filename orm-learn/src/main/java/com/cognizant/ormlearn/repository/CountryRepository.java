package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

@Repository

public interface CountryRepository extends CrudRepository<Country, String> {
	 @Query("select c from Country c where c.name like %?1%")
	 List<Country> findByName(@Param("name") String name);

}