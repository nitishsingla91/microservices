package com.nagarro.nes.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nagarro.nes.enums.ErrorCodeType;
import com.nagarro.nes.exceptions.ApplicationException;
import com.nagarro.nes.exceptions.UserNotFoundException;
import com.nagarro.nes.exceptions.UserUpdateFailedException;
import com.nagarro.nes.models.User;
import com.nagarro.nes.services.IUserService;
import com.nagarro.nes.store.UserStore;

@Service
public class UserServiceImpl implements IUserService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User getUserById(final String userID) throws ApplicationException {
		LOGGER.info("Get user with id:{}", userID);
		final Optional<User> optionalUser = UserStore.USERS_AVAILABLE.stream()
				.filter(user -> user.getUserId().equalsIgnoreCase(userID)).findAny();
		return optionalUser.orElseThrow(
				() -> new UserNotFoundException("User with requested ID not found", ErrorCodeType.USER_NOT_FOUND));
	}

	@Override
	public User addUser(final User request) throws UserUpdateFailedException {
		Optional<User> optionalUser = UserStore.USERS_AVAILABLE.stream()
				.filter(user -> user.getUserId().equalsIgnoreCase(request.getUserId())).findAny();
		if (optionalUser.isPresent()) {
			LOGGER.info("user id:{} already present", request.getUserId());
			throw new UserUpdateFailedException("User id already exists", ErrorCodeType.USER_UPDATE_FAILED);
		} else {
			LOGGER.debug("User id unique,insert user");
		}
		LOGGER.info("Add user to store with id:{}", request.getUserId());
		UserStore.USERS_AVAILABLE.add(request);
		return request;
	}

	@Override
	public Void deleteUser(final String userID) throws ApplicationException {
		LOGGER.info("Delete user with id:{}", userID);
		final Optional<User> optionalUser = UserStore.USERS_AVAILABLE.stream()
				.filter(user -> user.getUserId().equalsIgnoreCase(userID)).findFirst();
		final User userToBeDelete = optionalUser.orElseThrow(
				() -> new UserNotFoundException("User with requested ID not found", ErrorCodeType.USER_NOT_FOUND));
		UserStore.USERS_AVAILABLE.remove(userToBeDelete);
		LOGGER.debug("User with id: {} deleted Sucessfully", userID);
		return null;
	}

}
