package br.com.crudmongo.integrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.crudmongo.SpringbootStart;

/**
 * Base class to be used by integration test classes in order to use base functionalities and common methods.
 *
 * @author lucasandrade
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootStart.class })
@WebAppConfiguration(value="src/main/java")
public class BaseIntegrationTest {

	protected static final String CONTENT_TYPE_TEXTPLAIN = "text/plain;charset=UTF-8";
	protected static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";

	protected static final String URL_APP = "/crudmongo/";
	protected static final int DEFAULT_ID_LENGTH = 24;

	protected MockMvc mockMvc;
	protected ObjectMapper jsonMapper = new ObjectMapper();

	@Autowired
	protected WebApplicationContext webApplicationContext;

	/**
	 * Fires the {@link MockHttpServletRequestBuilder} object passed as parameter to the respective controller.
	 * Afterwards some asserts validations is done to guarantees that the request was processed correctly.
	 *
	 * @param request - The request to be performed.
	 * @param contentType - The expected response content type.
	 * @return A {@link MockHttpServletResponse} object that was answered by the Rest API.
	 * @throws Exception If something happen wrong.
	 */
	protected MockHttpServletResponse performRequest(MockHttpServletRequestBuilder request, String contentType) throws Exception {
		MockHttpServletResponse response = this.mockMvc.perform(request).andReturn().getResponse();

		assertNotNull(response);
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(response.getContentType(), contentType);

		return response;
	}
}
