package br.com.crudmongo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Auxiliary exception class to User flows.
 *
 * @author lucasandrade
 */
@Component
public class UserExceptionUtil {

	private static final String EXCEPTION_MESSAGES_PROPERTIES = "ExceptionMessages.properties";

	@Autowired
	private Properties properties;

	/**
	 * Gets the correspondent error message by it's property key.
	 *
	 * @param propertyKey - the key of the exception message to be loaded.
	 * @return the exception message description.
	 */
	public String getExceptionMessage(String propertyKey) {

		try {
			InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(EXCEPTION_MESSAGES_PROPERTIES);

			properties.load(resource);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties.getProperty(propertyKey);
	}
}
