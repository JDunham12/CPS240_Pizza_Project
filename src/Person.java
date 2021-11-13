
public class Person {

	private String name;
	private String username;
	private String password;
	private String address; //Should this remain String?
	private int phoneNumber; 
	
	//Default Constructor
	public Person() {
		this.name = "Unknown";
		this.username = "N/A";
		this.password = "N/A";
		this.address = "N/A";
		this.phoneNumber = 0;
	}
	
	//Constructor w/ Parameters
	public Person(String name, String username, String password, String address, int phoneNumber) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", username=" + username + ", password=" + password + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + "]";
	}
}
