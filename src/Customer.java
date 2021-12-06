
public class Customer extends Person {

	private String customerID;
	
	// Default Constructor
	public Customer() {
		super();
		this.customerID = "EFJ246";
	}
	
	// Constructor w/ Parameters
	public Customer(String lastID, String name, String username, String password, String address, String phoneNumber) {
		super(name, username, password, address, phoneNumber);
		this.customerID = "C" + (Integer.parseInt(lastID) + 1);
	}
	
	public String getCustomerID() {
		return customerID;
	}

	@Override
	public String toString() {
		return super.toString() + ":" + customerID;
	}

}
