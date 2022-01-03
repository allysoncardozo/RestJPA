package com.springjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springjpa.domain.Users;


@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Users, Long> {

	@Query("SELECT u FROM users u where email = ?1 and password = ?2")
	public Optional<Users> login(String email, String password);

	public Optional<Users> getUsersByEmailAndPassword(String email, String password);

	public Optional<Users> findAllUsersById(Long id);

	public List<Users> findTop2000ByOrderByIdDesc();
}
