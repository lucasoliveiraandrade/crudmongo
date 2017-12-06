package br.com.crudmongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.crudmongo.business.UserBusiness;
import br.com.crudmongo.collection.UserCollection;

@RestController
@RequestMapping("/crudmongo/user")
@SuppressWarnings("static-access")
public class UserController {

	@Autowired
	private UserBusiness business;

	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> insertNew(@RequestBody UserCollection user) throws Exception {
		String userId = business.create(user);

		return new ResponseEntity<String>(HttpStatus.CREATED).ok(userId);
	}

	@RequestMapping(value="/delete/{userId}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable String userId) throws Exception{
		business.delete(userId);

		return new ResponseEntity<String>(HttpStatus.OK).ok(userId);
	}

	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteAll() throws Exception{
		business.delete();

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlerException(Exception exception) {
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST).badRequest().body(exception.getMessage());
	}
}
