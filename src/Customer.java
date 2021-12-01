
public class Customer extends Person {

	private String customerID;
	private String test;
	
	// Default Constructor
	public Customer() {
		super();
		this.customerID = "EFJ246";
	}
	public String getCustomerID() {
		return customerID;
	}

	//Error from wrong input type. 
	// Constructor w/ Parameters
	public Customer(String lastID, String name, String username, String password, String address, String phoneNumber) {
		super(name, username, password, address, phoneNumber);
		this.customerID = "C" + (Integer.parseInt(lastID) + 1);
	}

	@Override
	public String toString() {
		return super.toString() + ":" + customerID;
	}

}
