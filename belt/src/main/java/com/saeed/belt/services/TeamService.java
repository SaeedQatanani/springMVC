package com.saeed.belt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.saeed.belt.models.Team;
import com.saeed.belt.repositories.TeamRepository;

@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRepo;
	public List<Team> allTeams(){
		return teamRepo.findAll();
	}
	public Team findTeamById(Long id) {
		Optional<Team> optionalTeam = teamRepo.findById(id);
		if(optionalTeam.isPresent()) {
			return optionalTeam.get();
		}
		return null;
	}
	public Team createTeam(Team t, BindingResult result) {
		ArrayList<String> days = new ArrayList<String>();
		days.add("Saturday");
		days.add("Sunday");
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wendesday");
		days.add("Thursday");
		days.add("Friday");
		if (t.getUsers()!=null) {
			if (t.getUsers().size()>9) {
				result.rejectValue("users","Invalid","The team is full!");
			}
		}
		if (!days.contains(t.getGameDay())) {
			result.rejectValue("gameDay","Invalid","Not a week day");
		}
		if(result.hasErrors()) {
            return null;
        }else {
        	return teamRepo.save(t);
        }
		
	}
	public Team updateTeam(Team t, BindingResult result) {
		ArrayList<String> days = new ArrayList<String>();
		days.add("Saturday");
		days.add("Sunday");
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wendesday");
		days.add("Thursday");
		days.add("Friday");
		if (t.getUsers().size()>9) {
    		result.rejectValue("users","Invalid","The team is full!");
		}
		if (!days.contains(t.getGameDay())) {
			result.rejectValue("gameDay","Invalid","Not a week day");
		}
		if(result.hasErrors()) {
            return null;
        }else {
        	return teamRepo.save(t);
        }
	}
	public Team updateTeam(Team t) {
        	return teamRepo.save(t);
	}
	public void deleteTeam(Long id) {
		teamRepo.deleteById(id);
	}
}
