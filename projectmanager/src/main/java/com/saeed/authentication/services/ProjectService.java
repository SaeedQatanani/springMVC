package com.saeed.authentication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saeed.authentication.models.Project;
import com.saeed.authentication.models.User;
import com.saeed.authentication.repositories.ProjectRepository;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepo;
    
    public List<Project> allProjects(){
    	return projectRepo.findAll();
    }
    
    public List<Project> allProjectsNotContaining(User user){
    	return projectRepo.findByUsersNotContains(user);
    }
    
    public List<Project> allProjectsContaining(User user){
    	return projectRepo.findAllByUsers(user);
    }
    
    public List<Project> allProjectsLeading(User user){
    	return projectRepo.findAllByTeamLead(user);
    }

    public Project findProjectById(Long id) {
    	Optional<Project> optionalProject = projectRepo.findById(id);
		if(optionalProject.isPresent()) {
			return optionalProject.get();
		}
		else {
			return null;
		}
    }

    public Project createProject(Project p) {
    	return projectRepo.save(p);
    }
    public Project updateProject(Project p) {
    	return projectRepo.save(p);
    }
    public void deleteProject(Long id) {
    	projectRepo.deleteById(id);
    }

   
}
