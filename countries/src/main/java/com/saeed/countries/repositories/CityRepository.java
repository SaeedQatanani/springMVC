package com.saeed.countries.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saeed.countries.models.City;

@Repository
public interface CityRepository extends CrudRepository <City, Integer>{
	
}
