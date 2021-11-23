
public class Customer extends Person {

	private String customerID;
	private String test;
	
	// Default Constructor
	public Customer() {
		super();
		this.customerID = "EFJ246";
	}

	// Constructor w/ Parameters
	public Customer(String name, String username, String password, String address, int phoneNumber, String customerID) {
		super(name, username, password, address, phoneNumber);
		this.customerID = "EFJ246";
	}

	@Override
	public String toString() {
		return "Customer " + super.toString() + " [customerID=" + customerID + "]";
	}

}
