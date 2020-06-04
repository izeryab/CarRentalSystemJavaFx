package main;

public class MyUser {
private String username;
private String name;
private String email;
private String contact;

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getContact() {
	return contact;
}

public void setContact(String contact) {
	this.contact = contact;
}

public MyUser(String username, String name, String email, String contact) {
this.username = username;
	this.name = name;
	this.email = email;
	this.contact = contact;
}

@Override
public String toString() {
	return "MyUser [username=" + username + ", name=" + name + ", email=" + email + ", contact=" + contact + "]";
}

public MyUser() {
}


}
