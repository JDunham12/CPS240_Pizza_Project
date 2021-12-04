
public class Person {

	private String name;
	private String username;
	private String password;
	private String address; 
	private String phoneNumber; 
	
	//Default Constructor
	public Person() {
		this.name = "Unknown";
		this.username = "N/A";
		this.password = "N/A";
		this.address = "N/A";
		this.phoneNumber = "N/A";
	}
	
	//Constructor w/ Parameters
	public Person(String name, String username, String password, String address, String phoneNumber) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return this.name;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getAddress() {
		return address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		return name + ":" + username + ":" + password + ":" + address
				+ ":" + phoneNumber;
	}
}
