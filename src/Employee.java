
public class Employee extends Person {

	private String employeeID;
	private String position; // Arraylist of positions?
	private boolean isFullTime;
	private double wage;
	private double yearToDateHours;

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
	public Employee(String name, String username, String password, String address, String phoneNumber,
			String position, double wage, double yearToDateHours, boolean isFullTime, String lastID) {
		super(name, username, password, address, phoneNumber);
		this.position = position;
		this.wage = wage;
		this.yearToDateHours = yearToDateHours;
		this.isFullTime = isFullTime;
		this.employeeID = "E" + (Integer.parseInt(lastID.substring(1, lastID.length())) + 1);
	}

	@Override
	public String toString() {
		return super.toString() + ":" + position + ":"
				+ wage + ":" + yearToDateHours + ":" + isFullTime + ":" + employeeID;
	}

}
