package br.com.crudmongo.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.crudmongo.collection.UserCollection;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void test01_shouldDeleteAllUsers() {
		userRepository.deleteAll();
		
		assertTrue(userRepository.count() == 0);
	}
	
	@Test
	public void test02_shouldCreateANewUser(){		
//		UserCollection user1 = new UserCollection("Lucas", 123);
//		UserCollection user2 = new UserCollection("Djodja", 456);
		
		assertNull(user1.getId());
		assertNull(user2.getId());
		
		userRepository.insert(user1);
		userRepository.insert(user2);
		
		assertNotNull(user1.getId());
		assertNotNull(user2.getId());
	}
	
	@Test
	public void test03_shouldFindAllUsers(){
		List<UserCollection> users = userRepository.findAll();
		
		assertTrue(users.size() == 2);
		assertTrue(users.get(0).getName().equals("Lucas"));
		assertTrue(users.get(1).getName().equals("Djodja"));
	}
}
