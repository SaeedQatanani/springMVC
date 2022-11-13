package com.saeed.countries.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saeed.countries.models.Language;

@Repository
public interface LanguageRepository extends CrudRepository <Language, Integer> {

}
