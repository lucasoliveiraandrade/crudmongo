package br.com.crudmongo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crudmongo.collection.UserCollection;
import br.com.crudmongo.repository.UserRepository;
import br.com.crudmongo.validation.UserValidator;

@Service
public class UserBusiness {
	
	@Autowired
	private UserValidator validator;
	
	@Autowired
	private UserRepository repository;
	
	public String create(UserCollection user) throws Exception {
		
		validator.isValid(user);
		
		return repository.save(user).getId();
	}
}