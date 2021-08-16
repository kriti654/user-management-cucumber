package com.rvy.store.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvy.store.dao.IUserRepository;
import com.rvy.store.entity.User;
import com.rvy.store.exceptions.UserException;

@Service
@Transactional
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepo;
	
	@Override
	public User getUserById(Integer userId) throws UserException {
		try {
			Optional<User> optional = 
									userRepo.findById(userId);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new PersistenceException("Invalid user ID");
			}			
			
		}catch(DataAccessException e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public List<User> getUsers() throws UserException {
		try {
	    	List<User> userList = userRepo.findAll();
	    	return userList;
	    } catch (DataAccessException e) {
	    	throw new UserException(e.getMessage(),e);
	    } 
	}

	@Override
	public User addUser(User user) throws UserException {
		try {
			 user.setUserId(null);
			 return userRepo.save(user);
		}catch(DataAccessException e){
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public User updateUser(User user) throws UserException {
		try {
			return userRepo.save(user);
		} catch(DataAccessException e) {
		  throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public boolean deleteUser(Integer userId) throws UserException {
		try {
			userRepo.deleteById(userId);
			return true;
		}catch(DataAccessException e) {
			throw new UserException(e.getMessage(),e);
		}
	}

}
