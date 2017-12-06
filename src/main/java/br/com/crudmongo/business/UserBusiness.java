package br.com.crudmongo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crudmongo.collection.UserCollection;
import br.com.crudmongo.repository.UserRepository;
import br.com.crudmongo.util.UserExceptionUtil;
import static br.com.crudmongo.util.ValidationExceptionProperties.USER_NOT_FOUND;
import br.com.crudmongo.validation.UserValidator;

@Service
public class UserBusiness {

	@Autowired
	private UserValidator validator;

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserExceptionUtil exceptionUtil;

	public String create(UserCollection user) throws Exception {
		validator.isValid(user);

		return repository.save(user).getId();
	}

	public void delete(String userId) throws Exception {
		validator.isUserIdValid(userId);

		UserCollection user = repository.findOne(userId);

		if(user == null) {
			throw new Exception(exceptionUtil.getExceptionMessage(USER_NOT_FOUND));
		}

		repository.delete(user);
	}

	public void delete() {
		repository.deleteAll();
	}
}