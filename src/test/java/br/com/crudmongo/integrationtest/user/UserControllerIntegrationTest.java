package br.com.crudmongo.integrationtest.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.crudmongo.SpringbootStart;
import br.com.crudmongo.collection.UserCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringbootStart.class })
@WebAppConfiguration(value="src/main/java")
public class UserControllerIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	private ObjectMapper jsonMapper;

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final String DEFAULT_URL_APP = "/crudmongo/user";

	@Before
	public void setup() throws Exception {
		this.jsonMapper = new ObjectMapper();

	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void name() {
	   ServletContext servletContext = webApplicationContext.getServletContext();

	   for (String string : webApplicationContext.getBeanDefinitionNames()) {
		   System.out.println(string);
	   }

	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(webApplicationContext.getBean("userController"));

	    Assert.assertNotNull(webApplicationContext);
	}

	@Test
	public void test1() throws Exception {
		MockHttpServletResponse response = this.mockMvc.perform(get(DEFAULT_URL_APP)).andReturn().getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(response.getContentType(), CONTENT_TYPE);
		List<UserCollection> responseObjectContent = jsonMapper.readValue(response.getContentAsString(), new TypeReference<List<UserCollection>>(){});
		Assert.assertEquals(responseObjectContent.size(), 1);
	}
}
