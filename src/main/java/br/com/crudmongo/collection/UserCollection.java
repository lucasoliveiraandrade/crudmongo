package br.com.crudmongo.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class UserCollection {
	
	@Id
	private String id;
	
	private String name;
	
	private int code;

	public UserCollection(String name, int code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "UserCollection [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
}
