package com.saeed.belt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saeed.belt.models.Team;
import com.saeed.belt.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findAll();
	Optional<User> findById(Long id);
	Optional<User> findByEmail(String email);
	List<User> findByJoinedTeamsNotContains(Team team);
}
