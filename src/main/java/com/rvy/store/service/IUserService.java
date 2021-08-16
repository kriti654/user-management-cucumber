package com.rvy.store.service;

import java.util.List;


import com.rvy.store.entity.User;
import com.rvy.store.exceptions.UserException;

public interface IUserService {
	public abstract User getUserById(Integer userId) throws UserException;
    public abstract List<User> getUsers() throws UserException;
    public abstract User addUser(User user) throws UserException;
    public abstract User updateUser(User user) throws UserException;
    public abstract boolean deleteUser(Integer userId) throws UserException;
}
