package com.nagarro.nes.exceptions.advices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nagarro.nes.exceptions.BadRequestException;
import com.nagarro.nes.exceptions.PaymentFailedException;
import com.nagarro.nes.exceptions.PaymentNotFoundException;
import com.nagarro.nes.exceptions.SystemException;
import com.nagarro.nes.models.BaseResponse;

/**
 * The Class ExceptionController. handle operation after api throw any exception
 *
 * @author chetanmahajan
 */
@ControllerAdvice(basePackages = "com.nagarro.nes")
public class ExceptionController extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	/**
	 * Handle application exception.
	 *
	 * @param ex       the ex
	 * @param request  the request
	 * @param response the response
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ PaymentNotFoundException.class })
	@ResponseBody
	public ResponseEntity<BaseResponse> handleException(final PaymentNotFoundException ex,
			final HttpServletRequest request, final HttpServletResponse response) {
		LOGGER.error("Exception with error code :{}:{}", ex.getError().getErrorCode(), ex);
		return new ResponseEntity<>(new BaseResponse<>(ex.getError().getErrorCode(), ex.getMessage()),
				ex.getError().getHttpStatusCode());
	}

	/**
	 * Handle application exception.
	 *
	 * @param ex       the ex
	 * @param request  the request
	 * @param response the response
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ PaymentFailedException.class })
	@ResponseBody
	public ResponseEntity<BaseResponse> handleException(final PaymentFailedException ex,
			final HttpServletRequest request, final HttpServletResponse response) {
		LOGGER.error("Exception with error code :{}:{}", ex.getError().getErrorCode(), ex);
		return new ResponseEntity<>(new BaseResponse<>(ex.getError().getErrorCode(), ex.getMessage()),
				ex.getError().getHttpStatusCode());
	}

	/**
	 * Handle system exception.
	 *
	 * @param ex       the ex
	 * @param request  the request
	 * @param response the response
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ SystemException.class })
	@ResponseBody
	public ResponseEntity<BaseResponse> handleException(final SystemException ex, final HttpServletRequest request,
			final HttpServletResponse response) {
		return new ResponseEntity<>(new BaseResponse<>(ex.getError().getErrorCode(), ex.getMessage()),
				ex.getError().getHttpStatusCode());
	}

	/**
	 * Handle validation exception.
	 *
	 * @param ex       the ex
	 * @param request  the request
	 * @param response the response
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ BadRequestException.class })
	@ResponseBody
	public ResponseEntity<BaseResponse> handleException(final BadRequestException ex, final HttpServletRequest request,
			final HttpServletResponse response) {
		LOGGER.debug("Bad Request Exception Controller in process:{}", ex);
		return new ResponseEntity<>(
				new BaseResponse<>(ex.getResponseMessages(), ex.getError().getErrorCode(), ex.getMessage()),
				ex.getError().getHttpStatusCode());
	}

}
