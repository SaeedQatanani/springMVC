package com.saeed.countries.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saeed.countries.models.Country;

@Repository
public interface CountryRepository extends CrudRepository <Country, Integer> {
	@Query(value="select countries.name, languages.percentage from countries join languages on countries.id = languages.country_id where languages.language = 'Slovene' order by languages.percentage desc", nativeQuery=true)
	List<Object[]> firstQuery();
	@Query(value="select countries.name as country_name , count(cities.name) as number_of_cities from countries join cities on countries.id = cities.country_id group by countries.name order by count(cities.name) desc", nativeQuery=true)
	List<Object[]> secondQuery();
	@Query(value="select cities.name, cities.population from countries join cities on countries.id = cities.country_id where countries.name ='Mexico' and cities.population > 500000 order by cities.population desc", nativeQuery=true)
	List<Object[]> thirdQuery();
	@Query(value="select countries.name, languages.language, languages.percentage from countries join languages on countries.id = languages.country_id where languages.percentage > 89 order by languages.percentage desc", nativeQuery=true)
	List<Object[]> fourthQuery();
	@Query(value="select name, surface_area, population from countries where surface_area <501 and population >100000", nativeQuery=true)
	List<Object[]> fifthQuery();
	@Query(value="select name, government_form, capital, life_expectancy from countries where government_form = 'Constitutional Monarchy' and capital>200 and life_expectancy >75", nativeQuery=true)
	List<Object[]> sixthQuery();
	@Query(value="select countries.name, cities.name, cities.district, cities.population from countries join cities on countries.id = cities.country_id where countries.name='Argentina' and cities.district = 'Buenos Aires' and cities.population >500000", nativeQuery=true)
	List<Object[]> seventhQuery();
	@Query(value="select region, count(name) as number_of_countries from countries group by region order by count(name) desc", nativeQuery=true)
	List<Object[]> eighthQuery();
}
