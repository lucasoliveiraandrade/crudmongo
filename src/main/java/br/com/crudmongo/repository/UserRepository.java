package br.com.crudmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.crudmongo.collection.UserCollection;

public interface UserRepository extends MongoRepository<UserCollection, Integer>{
	UserCollection findByName(String name);
}
