
public class Employee extends Person {

	private String employeeID;
	private String position; // Arraylist of positions?
	private boolean isFullTime;

	// Default constructor
	public Employee() {
		super();
		this.employeeID = "DEF123";
		this.position = "Cook";
		this.wage = 0.0;
		this.yearToDateHours = 0.0;
		this.isFullTime = true;
	}

	// Constructor w/ Parameters
	public Employee(String name, String username, String password, String address, int phoneNumber, String employeeID,
			String position, double wage, double yearToDateHours, boolean isFullTime) {
		super(name, username, password, address, phoneNumber);
		this.employeeID = employeeID;
		this.position = position;
		this.wage = wage;
		this.yearToDateHours = yearToDateHours;
		this.isFullTime = isFullTime;
	}

	@Override
	public String toString() {
		return "Employee " + super.toString() + " [employeeID=" + employeeID + ", position=" + position + ", wage="
				+ wage + ", yearToDateHours=" + yearToDateHours + ", isFullTime=" + isFullTime + "]";
	}

}
