package com.culqi.tokens.advice;


import com.culqi.tokens.exception.AppException;
import com.culqi.tokens.exception.BadRequestException;
import com.culqi.tokens.model.payload.response.ApiCulqiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CulqiControllerAdvice {

	private Logger logger = LoggerFactory.getLogger(CulqiControllerAdvice.class);

	@Autowired
	private MessageSource messageSource;


	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiCulqiResponse processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<ObjectError> allErrors = result.getAllErrors();
		ApiCulqiResponse response = new ApiCulqiResponse();
		response.setSuccess(false);
		response.setData(processAllErrors(allErrors).stream());
		return response;
	}

	/**
	 * Utility Method to generate localized message for a list of field errors
	 * @param allErrors the field errors
	 * @return the list
	 */
	private List<String> processAllErrors(List<ObjectError> allErrors) {
		return allErrors.stream().map(this::resolveLocalizedErrorMessage).collect(Collectors.toList());
	}

	/**
	 * Resolve localized error message. Utiity method to generate a localized error
	 * message
	 * @param objectError the field error
	 * @return the string
	 */
	private String resolveLocalizedErrorMessage(ObjectError objectError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
		logger.info(localizedErrorMessage);
		return localizedErrorMessage;
	}

	@ExceptionHandler(value = AppException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ApiCulqiResponse handleAppException(AppException ex) {
		ApiCulqiResponse ApiCulqiResponse = new ApiCulqiResponse();
		ApiCulqiResponse.setSuccess(false);
		ApiCulqiResponse.setData(ex.getMessage());
		return ApiCulqiResponse;
	}

	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiCulqiResponse handleBadRequestException(BadRequestException ex) {
		ApiCulqiResponse ApiCulqiResponse = new ApiCulqiResponse();
		ApiCulqiResponse.setSuccess(false);
		ApiCulqiResponse.setData(ex.getMessage());
		return ApiCulqiResponse;
	}

}
