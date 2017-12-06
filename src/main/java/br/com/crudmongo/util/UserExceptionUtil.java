package br.com.crudmongo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExceptionUtil {

	private static final String EXCEPTION_MESSAGES_PROPERTIES = "ExceptionMessages.properties";

	@Autowired
	private Properties properties;

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
