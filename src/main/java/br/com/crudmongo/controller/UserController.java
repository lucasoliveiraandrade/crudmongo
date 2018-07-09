package br.com.crudmongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.crudmongo.business.UserBusiness;
import br.com.crudmongo.collection.UserCollection;

/**
 * User controller class to provide Rest APIs for CRUD operations.
 *
 * @author lucasandrade
 */
@RestController
@RequestMapping("/crudmongo/user")
@SuppressWarnings("static-access")
public class UserController {

	@Autowired
	private UserBusiness business;

	/**
	 * POST Rest API to create new Users.
	 *
	 * @param user - the {@link UserCollection} object to be created.
	 * @return a {@link ResponseEntity} containing the Id of the created {@link UserCollection}.
	 * @throws Exception if the User parameter is not valid.
	 */
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insert(@RequestBody UserCollection user) throws Exception {
		String userId = business.create(user);
		return new ResponseEntity<String>(HttpStatus.CREATED).ok(new Gson().toJson(userId));
	}

	/**
	 * DELETE Rest API to remove a specific User.
	 *
	 * @param userId - the attribute Id of the User to be deleted.
	 * @return a {@link ResponseEntity} containing the Id of the removed User.
	 * @throws Exception if the UserId parameter is not valid.
	 */
	@CrossOrigin
	@RequestMapping(value="/delete/{userId}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable String userId) throws Exception{
		business.delete(userId);

		return new ResponseEntity<String>(HttpStatus.OK).ok(new Gson().toJson(userId));
	}

	/**
	 * DELETE Rest API to remove all Users.
	 *
	 * @return a {@link ResponseEntity} containing Success status.
	 */
	@CrossOrigin
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteAll() {
		business.delete();

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * GET Rest API to find a User.
	 *
	 * @param userId - the attribute Id of the User to be founded.
	 * @return a {@link ResponseEntity} containing the User founded.
	 * @throws Exception if the User Id parameter is not valid.
	 */
	@CrossOrigin
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	public ResponseEntity<UserCollection> find(@PathVariable String userId) throws Exception{
		UserCollection user = business.find(userId);

		return new ResponseEntity<UserCollection>(HttpStatus.FOUND).ok(user);
	}

	/**
	 * GET Rest API to find all Users.
	 *
	 * @return a {@link ResponseEntity} containing all Users founded.
	 */
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserCollection>> findAll() {
		List<UserCollection> users = business.findAll();

		return new ResponseEntity<List<UserCollection>>(HttpStatus.FOUND).ok(users);
	}

	/**
	 * UPDATE Rest API to modify an existent User.
	 *
	 * @param user - an {@link UserCollection} object to be updated.
	 * @return a {@link ResponseEntity} containing the Id of the updated User.
	 * @throws Exception if the User parameter is not valid.
	 */
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<String> update(@RequestBody UserCollection user) throws Exception{
		business.update(user);

		return new ResponseEntity<>(HttpStatus.OK).ok(user.getId());
	}

	/**
	 * Handles all {@link Exception} that are thrown in this class.
	 *
	 * @param exception - the {@link Exception} object thrown.
	 * @return a {@link ResponseEntity} containing the exception message and status 400.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlerException(Exception exception) {
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST).badRequest().body(exception.getMessage());
	}
}
