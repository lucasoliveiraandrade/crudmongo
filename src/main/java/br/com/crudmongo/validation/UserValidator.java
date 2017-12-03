package br.com.crudmongo.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crudmongo.collection.UserCollection;
import br.com.crudmongo.util.UserExceptionUtil;

import static br.com.crudmongo.util.ValidationExceptionProperties.*;

@Component
public class UserValidator {
	
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
}
