package br.com.crudmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.crudmongo.collection.UserCollection;

/**
 * A User repository class to make operations in the database.
 *
 * @author lucasandrade
 */
@Repository
public interface UserRepository extends MongoRepository<UserCollection, String>{
	UserCollection findByName(String name);
}
