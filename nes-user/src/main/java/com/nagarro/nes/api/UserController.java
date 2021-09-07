package com.nagarro.nes.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nes.exceptions.ApplicationException;
import com.nagarro.nes.models.BaseResponse;
import com.nagarro.nes.models.User;
import com.nagarro.nes.services.IUserService;
import com.nagarro.nes.validators.UserInputValidator;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private UserInputValidator inputValidator;

	@GetMapping("{userID}")
	public ResponseEntity<BaseResponse<User>> getUserById(@PathVariable("userID") final String userID)
			throws ApplicationException {
		LOGGER.info("Request for user with id:{}", userID);
		return ResponseEntity.ok(new BaseResponse<>(this.userService.getUserById(userID.trim())));
	}

	@PostMapping
	public ResponseEntity<BaseResponse<User>> registerNewUser(@Valid @RequestBody final User user,
			final BindingResult requestBindingResult) throws ApplicationException {
		LOGGER.info("Received add user request");
		inputValidator.checkBindingResults(requestBindingResult);
		return ResponseEntity.ok(new BaseResponse<>(this.userService.addUser(user)));
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteUser(@RequestParam("userID") final String userID)
			throws ApplicationException {
		LOGGER.info("Recieved Delete user request");
		return ResponseEntity.ok(this.userService.deleteUser(userID.trim()));
	}

}
