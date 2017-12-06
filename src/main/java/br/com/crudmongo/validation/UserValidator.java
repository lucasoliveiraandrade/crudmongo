package br.com.crudmongo.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crudmongo.collection.UserCollection;
import br.com.crudmongo.util.UserExceptionUtil;

import static br.com.crudmongo.util.ValidationExceptionProperties.*;

@Component
public class UserValidator {

	private static final int DEFAULT_ID_LENGTH = 24;

	@Autowired
	private UserExceptionUtil exceptionUtil;

	public void isValid(UserCollection user) throws Exception {
		if(user == null) {
			throw new Exception(exceptionUtil.getExceptionMessage(VALIDATION_USER));
		}

		if (StringUtils.isBlank(user.getName())) {
			throw new Exception(exceptionUtil.getExceptionMessage(VALIDATION_USER_NAME));
		}

		if(user.getCode() == null) {
			throw new Exception(exceptionUtil.getExceptionMessage(VALIDATION_USER_CODE));
		}

		if(user.getValue() == null) {
			throw new Exception(exceptionUtil.getExceptionMessage(VALIDATION_USER_VALUE));
		}

		if(user.getBirthday() == null) {
			throw new Exception(exceptionUtil.getExceptionMessage(VALIDATION_USER_BIRTHDAY));
		}
	}

	public void isUserIdValid(String userId) throws Exception{
		if(userId == null || userId.length() != DEFAULT_ID_LENGTH) {	
			throw new Exception(exceptionUtil.getExceptionMessage(VALIDATION_USER_ID));
		}
	}
}
