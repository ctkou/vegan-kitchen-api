/**
 * This class is generated by jOOQ
 */
package model.mapping.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User implements Serializable {

	private static final long serialVersionUID = 1618127037;

	private Integer userId;
	private String  userName;
	private String  email;
	private String  password;

	public User() {}

	public User(User value) {
		this.userId = value.userId;
		this.userName = value.userName;
		this.email = value.email;
		this.password = value.password;
	}

	public User(
		Integer userId,
		String  userName,
		String  email,
		String  password
	) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("User (");

		sb.append(userId);
		sb.append(", ").append(userName);
		sb.append(", ").append(email);
		sb.append(", ").append(password);

		sb.append(")");
		return sb.toString();
	}
}
