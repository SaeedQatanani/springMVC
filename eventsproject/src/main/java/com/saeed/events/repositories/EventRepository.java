package com.saeed.events.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saeed.events.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>  {
	List<Event> findAll();
	Optional<Event> findById(Long id);
	List<Event> findAllByState(String state);
	List<Event> findByStateNotContains(String state);

}
