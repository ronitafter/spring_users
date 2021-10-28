package com.ronit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ronit.beans.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
	List<User> findByAge(int age);
	List<User> findByAgeAndName(int age, String name);
	boolean existsByName(String name);


}
