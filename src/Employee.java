
public class Employee extends Person {

	private String employeeID;
	private String position; 
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
		public Employee(String lastID, String name, String username, String password, String address, String phoneNumber,
				String position, double wage, boolean isFullTime) {
			super(name, username, password, address, phoneNumber);
			this.position = position;
			this.wage = wage;
			yearToDateHours = 0;
			this.isFullTime = isFullTime;
			this.employeeID = "E" + (Integer.parseInt(lastID) + 1);
		}
		
	// Constructor w/ Parameters
	public Employee(String lastID, String name, String username, String password, String address, String phoneNumber,
			String position, double wage, double yearToDateHours, boolean isFullTime) {
		super(name, username, password, address, phoneNumber);
		this.position = position;
		this.wage = wage;
		this.yearToDateHours = yearToDateHours;
		this.isFullTime = isFullTime;
		this.employeeID = "E" + (Integer.parseInt(lastID) + 1);
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean getIsFullTime() {
		return isFullTime;
	}

	public void setFullTime(boolean isFullTime) {
		this.isFullTime = isFullTime;
	}

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public double getYearToDateHours() {
		return yearToDateHours;
	}

	public void setYearToDateHours(double yearToDateHours) {
		this.yearToDateHours = yearToDateHours;
	}

	@Override
	public String toString() {
		return super.toString() + ":" + position + ":"
				+ wage + ":" + yearToDateHours + ":" + isFullTime + ":" + employeeID;
	}

}
