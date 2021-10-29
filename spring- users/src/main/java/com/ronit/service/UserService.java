package com.ronit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronit.beans.User;
import com.ronit.exceptions.InvalidOperationException;
import com.ronit.exceptions.UserExceptions;
import com.ronit.repositories.UsersRepository;

@Service
public class UserService {

	@Autowired
	private UsersRepository usersRepository;

	public void addUser(User user) throws InvalidOperationException {
		if (usersRepository.existsByName(user.getName())) {
			throw new InvalidOperationException("addUser - cannot update user. this name already exists");
		} else {
			usersRepository.save(user);
		}
	}

	public void updateUser(User user) throws UserExceptions, InvalidOperationException {
		if (!(user.getId() > 0 && usersRepository.existsById(user.getId()))) {
			throw new UserExceptions("updateUser faild - user does not exist");
		} else {
			usersRepository.save(user);
		}
	}

	public void updateUser2(User user) throws UserExceptions, InvalidOperationException {

		Optional<User> opt = usersRepository.findById(user.getId());
		if (opt.isPresent()) {
			User userDb = opt.get();
			userDb.setAge(user.getAge());
			userDb.setName(user.getName());
		} else {
			throw new UserExceptions("updateUser faild - user does not exist");
		}

	}

//	public User getUser(int id) {
//		return usersRepository.findById(id).orElse(null);
////		return usersRepository.getById(id);
//
//	}

	public User getUser(int id) throws UserExceptions {
		return usersRepository.findById(id).orElseThrow(() -> new UserExceptions("getUser failed - not found"));
	}

	public List<User> getAllUsers() {
		return usersRepository.findAll();

	}

	public List<User> getUsersByAge(int age) {
		return usersRepository.findByAge(age);
	}

	public List<User> getUsersByAgeAndName(int age, String name) {
		return usersRepository.findByAgeAndName(age, name);

	}

	public void deleteUser(int id) throws UserExceptions {
		if (usersRepository.existsById(id)) {
			usersRepository.deleteById(id);
		} else {
			throw new UserExceptions("deleteUser failed - user does not exist");
		}
	}

}
