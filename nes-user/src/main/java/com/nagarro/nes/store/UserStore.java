package com.nagarro.nes.store;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nagarro.nes.enums.UserType;
import com.nagarro.nes.models.User;

@Component
public class UserStore {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserStore.class);

	public static final List<User> USERS_AVAILABLE = new ArrayList<>();

	@PostConstruct
	public void init() {
		LOGGER.debug("Storing some users before initialisation");
		USERS_AVAILABLE.add(new User("aayush096", "Aayush", "Chalana", "abcd", UserType.CUSTOMER,
				"Chandni chownk,Delhi", "aayush@gmail.com", 9745646356L));
		USERS_AVAILABLE.add(new User("chetan096", "Chetan", "Mahajan", "abcd", UserType.ADMIN, "Chandni chownk,Delhi",
				"chetan@gmail.com", 9745546358L));
		USERS_AVAILABLE.add(new User("Adhish096", "Adhish", "Kapoor", "abcd", UserType.DELIEVERY_MANAGER,
				"Chandni chownk,Delhi", "Adhish@gmail.com", 9745126356L));
	}

}
