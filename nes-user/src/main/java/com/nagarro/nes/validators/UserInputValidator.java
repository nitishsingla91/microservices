package com.nagarro.nes.validators;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.nagarro.nes.enums.ErrorCodeType;
import com.nagarro.nes.exceptions.BadRequestException;
import com.nagarro.nes.models.ValidationResponseMessage;

/**
 * The Class ProductControllerInputValidator.
 *
 * @author chetanmahajan
 */
@Component
public class UserInputValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserInputValidator.class);

	/**
	 * Check binding results.
	 *
	 * @param requestBindingResults the request binding results
	 * @throws BadRequestException the bad request exception
	 */
	public void checkBindingResults(final BindingResult requestBindingResults) throws BadRequestException {
		if (requestBindingResults.hasErrors()) {
			LOGGER.debug("Request has errors");
			final List<ObjectError> objectErrors = requestBindingResults.getAllErrors();
			final List<ValidationResponseMessage> messages = new ArrayList<>();

			for (final ObjectError objectError : objectErrors) {
				messages.add(new ValidationResponseMessage(((FieldError) objectError).getField(),
						objectError.getDefaultMessage()));
			}
			throw new BadRequestException(ErrorCodeType.INVALID_REQUEST, messages);
		}
	}

}
