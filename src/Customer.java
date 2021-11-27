
public class Customer extends Person {

	private String customerID;
	private String test;
	
	// Default Constructor
	public Customer() {
		super();
		this.customerID = "EFJ246";
	}

	// Constructor w/ Parameters
	public Customer(String name, String username, String password, String address, String phoneNumber, String lastID) {
		super(name, username, password, address, phoneNumber);
		this.customerID = "C" + (Integer.parseInt(lastID) + 1);
	}

	@Override
	public String toString() {
		return super.toString() + ":" + customerID;
	}

}
