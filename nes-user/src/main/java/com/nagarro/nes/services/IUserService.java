package com.nagarro.nes.services;

import com.nagarro.nes.exceptions.ApplicationException;
import com.nagarro.nes.exceptions.UserUpdateFailedException;
import com.nagarro.nes.models.User;

public interface IUserService {

	User getUserById(String userID) throws ApplicationException;

	User addUser(User user) throws UserUpdateFailedException;

	Void deleteUser(String userID) throws ApplicationException;

}
