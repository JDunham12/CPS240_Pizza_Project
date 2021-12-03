import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//test
public class Main {
	static Map<String, Person> personDatabase = new LinkedHashMap<String, Person>();
	static File file = new File("Program_Files\\PersonData.txt"); 
	public static void main(String[] args) {

		// map used to add customers and employees to text document to store records
		// after program closes
		
		
		// points to PersonData.txt whether or not it exists yet
		
		// calls method to collect data from given text document and populates given map
		// with contents
		loadPersonDatabase();

		// content to fill customer/employee database with
		// must be added one at a time
		Customer cust1 = new Customer(getNextID(personDatabase), "John Smith", "JSmith78", "Password",
				"p sherman 42, wallaby way sydney", "1234567890");
		Customer cust2 = new Customer(getNextID(personDatabase), "Joe Shmoe", "xXJShmoeXx", "password",
				"p sherman 43 wallaby way sydney", "1891230101");
		Customer cust3 = new Customer(getNextID(personDatabase), "John Smith", "JSmith79", "P@ssword", "The moon",
				"8115552022");
		Employee emp1 = new Employee(getNextID(personDatabase), "Jill Lamill", "JLamill", "Password",
				"p sherman 42, wallaby way sydney", "1234567890", "Cashier", 11.50, 550, true);
		Employee emp2 = new Employee(getNextID(personDatabase), "Mary LastName", "MLAstname", "password",
				"p sherman 43 wallaby way sydney", "1891230101", "Pizza Maker", 11.50, 550, true);
		Employee emp3 = new Employee(getNextID(personDatabase), "Sam Mus", "Metroid", "P@ssword", "The moon",
				"8115552022", "Manager", 20.00, 5, false);
		// System.out.println(cust1);

		// uncomment any line below to add any of the above objects to text document
		// addObjectToFile(cust1, file);
		// addObjectToFile(cust2, file);
		// addObjectToFile(cust3, file);
		// addObjectToFile(emp1, file);
		// addObjectToFile(emp2, file);
		// addObjectToFile(emp3, file);

	}

	// can be made generic to add other ojects to files, or can instead have all
	// files and db added
	// fills given map with data from given : delimited text file
	public static void loadPersonDatabase() {
		Scanner input = null;
		boolean success = false; // used to check to end while loop if file exists, or after creation
		while (success != true) {
			try {
				input = new Scanner(file); // opens scanner to read file
				success = true; // allows loop to end
			} catch (FileNotFoundException fnfe) { // checks if file does not exist
				createFile(file); // makes file if did not already exist
			}
		}
		int pageLineCounter = 0;
		// processes entire text document and makes employee and customer objects from
		// each line of data then stores in database
		while (input.hasNext()) { // runs until end of file is reached
			pageLineCounter++; // starts count at 1 and iterates for each line
			String line = input.nextLine(); // advances to next lime and stores entire line in String "line"
			String[] ln = line.split(":", 0); // splits line by colons and stores into ln array
			for (String s : ln) { // for each string in ln array
				// System.out.println(s); //used to test what parts were separated by split
			}
			if (ln[0].charAt(0) == 'C') { // determines if line in persondata.txt is a customer by number of elements
				// makes customer object from pieces split into ln array by : delimiter
				Customer cust = new Customer(getNextID(personDatabase), ln[1], ln[2], ln[3], ln[4], ln[5]);
				// Customer(0 String name, 1 String username, 2 String password, 3 String
				// address, 4 String phoneNumber, 5 String lastID)<--------------Customer Format

				personDatabase.put(cust.getCustomerID(), cust); // adds to map with CustomerID as key, and customer object as value
				System.out.println("added customer from file to map"); // used to indicate that addition succeeds
			} else if (ln[0].charAt(0) == 'E') { // determines if line in persondata.txt is an employee by number of
													// elements
				Employee emp = null;
				if (ln[9].equalsIgnoreCase("True")) { // full time employees
					emp = new Employee(getNextID(personDatabase), ln[1], ln[2], ln[3], ln[4], ln[5], ln[6],
							Double.parseDouble(ln[7]), Double.parseDouble(ln[8]), false);
					// Employee(0 String name, 1 String username, 2 String password, 3 String
					// address, 4 String phoneNumber, 5 String position, 6 double wage, 7 double
					// yearToDateHours, 8 boolean isFullTime, 9 String lastID)<--Employee Format

				} else { // part time employees
					emp = new Employee(getNextID(personDatabase), ln[1], ln[2], ln[3], ln[4], ln[5], ln[6],
							Double.parseDouble(ln[7]), Double.parseDouble(ln[8]), true);
					// Employee(0 String name, 1 String username, 2 String password, 3 String
					// address, 4 String phoneNumber, 5 String position, 6 double wage, 7 double
					// yearToDateHours, 8 boolean isFullTime, 9 String lastID)<--Employee Format

				}
				if (emp != null) {
					personDatabase.put(emp.getEmployeeID(), emp); // adds to map with EmployeeID as key, and employee object as value
				}
				System.out.println("added employee from file to map"); // test to show successful addition
			} else {
				System.out.println("Data length of " + ln.length + " in line " + pageLineCounter
						+ " is invalid for customer or employee."); // if ln.length doesn't match options
			}
		}
		if (input != null) {
			input.close(); // closes scanner if opened
		}
	}

	// gives the numbers to be used for employeeID and customerID based on the last
	// key in map passed as a parameter
	public static String getNextID(Map<String, Person> PersonDb) {
		String ID = "";
		Set<String> keyset = PersonDb.keySet(); // gets set of keys from map
		Iterator<String> it = keyset.iterator(); // Iterator for keyset
		while (it.hasNext()) { // runs until end of keyset
			ID = it.next(); // sets ID = to next key in set. ID ends being last ID in set
		}
		if (ID == "") { // if no ID was in the map
			return "99999"; // if no prior entry exists, returns 99999 to start count at 100000
		} else { // if an ID was in the map
			return ID.substring(1, ID.length()); // returns key without first character (removes E/C)
		}
	}
	
	public static String checkLoginCredentials(String u, String p){
		for(String s: personDatabase.keySet()) {
			if(u.compareTo(personDatabase.get(s).getUsername()) == 0) {
				if(p.compareTo(personDatabase.get(s).getPassword()) == 0) {
					return s;
				}else {
					System.out.println("wrong password");
				}
			}
		}
		return null;
	}

	public static void createFile(File f) { // creates a file based on location, name and type of f (txt)
		try {
			FileWriter dataWriter = new FileWriter(f, true); // creates text file based on f
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String formatCustomerForFile(Customer c) {
		String s = (c.getCustomerID() + ":" + c.getName() + ":" + c.getUsername() + ":" + c.getPassword() + ":"
				+ c.getAddress() + ":" + c.getPhoneNumber());
		return s;
	}

	public static String formatEmployeeForFile(Employee e) {
		String s = (e.getEmployeeID() + ":" + e.getName() + ":" + e.getUsername() + ":" + e.getPassword() + ":"
				+ e.getAddress() + ":" + e.getPhoneNumber() + ":" + e.getPosition() + ":" + e.getWage() + ":"
				+ e.getYearToDateHours() + ":" + e.getIsFullTime());
		return s;
	}
	/*
	public static String formatOrderForFile(Orders o) {
		String s = (o.getEmployeeID() + ":" + o.getName() + ":" + o.getUsername() + ":" + o.getPassword() + ":"
				+ o.getAddress() + ":" + o.getPhoneNumber() + ":" + o.getPosition() + ":" + o.getWage() + ":"
				+ o.getYearToDateHours() + ":" + o.getIsFullTime());
		return s;
	}*/

	public static<E> void addObjectToFile(E e, File file) {
		String s = "";
		if(e instanceof Customer && file.getPath().equals("Program_Files\\PersonData.txt")) {
			s = formatCustomerForFile((Customer) e);
		}else if(e instanceof Employee&& file.getPath().equals("Program_Files\\PersonData.txt")) {
			s = formatEmployeeForFile((Employee) e);
		}
		try (FileWriter fw = new FileWriter(file, true); 
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);) {
			pw.println(s); // uses toSting of Customer/Employee to write to add each line to
			// the bottom of the text document

		} catch (IOException i) {
			System.out.println("IO Exception");
		}

	}
}
