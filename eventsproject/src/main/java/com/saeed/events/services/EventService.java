package com.saeed.events.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saeed.events.models.Event;
import com.saeed.events.repositories.EventRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepo;
	
	public List<Event> allEvents(){
		return eventRepo.findAll();
	}
	
	public List<Event> eventsInYourState(String state){
		return eventRepo.findAllByState(state);
	}
	
	public List<Event> eventsNotInYourState(String state){
		return eventRepo.findByStateNotContains(state);
	}

    public Event findEventById(Long id) {
    	Optional<Event> optionalEvent = eventRepo.findById(id);
		if(optionalEvent.isPresent()) {
			return optionalEvent.get();
		}
		else {
			return null;
		}
    }

    public Event createEvent(Event p) {
    	return eventRepo.save(p);
    }
    public Event updateEvent(Event p) {
    	return eventRepo.save(p);
    }
    public void deleteEvent(Long id) {
    	eventRepo.deleteById(id);
    }
	
}
