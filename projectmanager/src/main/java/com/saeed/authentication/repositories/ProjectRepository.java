package com.saeed.authentication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saeed.authentication.models.Project;
import com.saeed.authentication.models.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAll();
    Optional<Project> findById(Long id);
    List<Project> findAllByUsers(User user);
    List<Project> findByUsersNotContains(User user);
    List<Project> findAllByTeamLead(User user);
}
