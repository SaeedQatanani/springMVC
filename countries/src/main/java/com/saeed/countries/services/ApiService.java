package com.saeed.countries.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saeed.countries.repositories.CityRepository;
import com.saeed.countries.repositories.CountryRepository;
import com.saeed.countries.repositories.LanguageRepository;

@Service
public class ApiService {
	@Autowired
	CountryRepository countryRepo;
	@Autowired
	CityRepository cityRepo;
	@Autowired
	LanguageRepository languageRepo;
	
	public List<Object[]> q1(){
    	return countryRepo.firstQuery();
    }
	public List<Object[]> q2(){
    	return countryRepo.secondQuery();
    }
	public List<Object[]> q3(){
    	return countryRepo.thirdQuery();
    }
	public List<Object[]> q4(){
    	return countryRepo.fourthQuery();
    }
	public List<Object[]> q5(){
    	return countryRepo.fifthQuery();
    }
	public List<Object[]> q6(){
    	return countryRepo.sixthQuery();
    }
	public List<Object[]> q7(){
    	return countryRepo.seventhQuery();
    }
	public List<Object[]> q8(){
    	return countryRepo.eighthQuery();
    }
 
}
