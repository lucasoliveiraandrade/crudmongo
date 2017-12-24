package br.com.crudmongo.integrationtest.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.crudmongo.collection.UserCollection;
import br.com.crudmongo.integrationtest.BaseIntegrationTest;

/**
 * Integration test for User CRUD flows.
 *
 * @author lucasandrade
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerIntegrationTest extends BaseIntegrationTest {
	private static final String REQUEST_BODY_POST = "{ \"name\": \"postman\", \"birthday\":\"2012-04-23T18:25:43.511Z\", \"value\":123.2, \"code\":123 }";
	private static final String REQUEST_BODY_PUT = "{ \"id\": \"%s\", \"name\": \"postman updated\", \"birthday\":\"2015-07-20T18:25:43.511Z\", \"value\":456.7, \"code\":456 }";

	private static final String URL_APP_USER = URL_APP + "user";
	private static final String URL_APP_USER_DELETE_ALL = URL_APP_USER + "/delete";

	private static String createdUserId;

	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

	    ServletContext servletContext = webApplicationContext.getServletContext();

	    assertNotNull(servletContext);
	    assertTrue(servletContext instanceof MockServletContext);
	    assertNotNull(webApplicationContext);
	}

	@Test
	public void test00_shouldDeleteAllUsers() throws Exception {
		performRequest(delete(URL_APP_USER_DELETE_ALL), null);
	}

	@Test
	public void test01_shouldCreateANewUser() throws Exception {
		MockHttpServletRequestBuilder request = post(URL_APP_USER).contentType(MediaType.APPLICATION_JSON).content(REQUEST_BODY_POST);

		MockHttpServletResponse response = performRequest(request, CONTENT_TYPE_TEXTPLAIN);

		assertNotNull(response.getContentAsString());
		assertEquals(response.getContentAsString().length(), DEFAULT_ID_LENGTH);

		createdUserId = response.getContentAsString();
	}

	@Test
	public void test02_shouldGetAllUsers() throws Exception {
		MockHttpServletResponse response = performRequest(get(URL_APP_USER), CONTENT_TYPE_JSON);

		List<UserCollection> responseObj = jsonMapper.readValue(response.getContentAsString(), new TypeReference<List<UserCollection>>(){});

		assertNotNull(responseObj);
		assertEquals(responseObj.size(), 1);
	}

	@Test
	public void test03_shouldGetAndUpdateTheCreatedUser() throws Exception {
		MockHttpServletRequestBuilder request = get(URL_APP_USER +"/"+ createdUserId);
		MockHttpServletResponse response = performRequest(request, CONTENT_TYPE_JSON);

		UserCollection expectedObj = jsonMapper.readValue(REQUEST_BODY_POST, UserCollection.class);
		UserCollection responseObj = jsonMapper.readValue(response.getContentAsString(), UserCollection.class);

		assertNotNull(responseObj);
		assertEquals(responseObj.getId(), createdUserId);
		assertEquals(responseObj.getName(), expectedObj.getName());
		assertEquals(responseObj.getCode(), expectedObj.getCode());
		assertEquals(responseObj.getValue(), expectedObj.getValue());
		assertEquals(responseObj.getBirthday(), expectedObj.getBirthday());

		responseObj.setName(expectedObj.getName());
		responseObj.setValue(expectedObj.getValue());
		responseObj.setCode(expectedObj.getCode());
		responseObj.setBirthday(expectedObj.getBirthday());

		request = put(URL_APP_USER).content(String.format(REQUEST_BODY_PUT, createdUserId)).contentType(MediaType.APPLICATION_JSON);
		response = performRequest(request, CONTENT_TYPE_TEXTPLAIN);

		assertEquals(response.getContentAsString(), createdUserId);
	}

	@Test
	public void test04_shouldCheckTheUserModifications() throws Exception {
		MockHttpServletRequestBuilder request = get(URL_APP_USER +"/"+ createdUserId);
		MockHttpServletResponse response = performRequest(request, CONTENT_TYPE_JSON);

		UserCollection responseObj = jsonMapper.readValue(response.getContentAsString(), UserCollection.class);
		UserCollection expectedObj = jsonMapper.readValue(REQUEST_BODY_PUT, UserCollection.class);

		assertEquals(responseObj.getName(), expectedObj.getName());
		assertEquals(responseObj.getCode(), expectedObj.getCode());
		assertEquals(responseObj.getValue(), expectedObj.getValue());
		assertEquals(responseObj.getBirthday(), expectedObj.getBirthday());
	}

	@Test
	public void test05_shouldDeleteTheUserCreated() throws Exception {
		MockHttpServletRequestBuilder request = delete(URL_APP_USER_DELETE_ALL +"/"+ createdUserId);
		MockHttpServletResponse response = performRequest(request, CONTENT_TYPE_TEXTPLAIN);

		assertEquals(response.getContentAsString(), createdUserId);
	}
}
