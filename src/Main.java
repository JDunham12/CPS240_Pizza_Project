import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//test
public class Main {
	public static void main(String[] args) {
		Map<String, Person> database = new LinkedHashMap<String, Person>();
		File file = new File("Program_Files\\PersonData.txt");
		loadPersonDatabase(database, file);
		Customer cust1 = new Customer("John Smith", "JSmith78", "Password", "p sherman 42, wallaby way sydney",
				"1234567890", getNextID(database));
		Customer cust2 = new Customer("Joe Shmoe", "xXJShmoeXx", "password", "p sherman 43 wallaby way sydney",
				"1891230101", getNextID(database));
		Customer cust3 = new Customer("John Smith", "JSmith79", "P@ssword", "The moon", "8115552022", getNextID(database));
		Employee emp1 = new Employee("Jill Lamill", "JLamill", "Password", "p sherman 42, wallaby way sydney",
				"1234567890", "cashier", 11.50, 550, true, getNextID(database));
		Employee emp2 = new Employee("Mary LastName", "MLAstname", "password", "p sherman 43 wallaby way sydney",
				"1891230101", "Pizza Maker", 11.50, 550, true, getNextID(database));
		Employee emp3 = new Employee("Sam Mus", "Metroid", "P@ssword", "The moon", "8115552022", "Manager", 20.00, 5, false, getNextID(database));
		// System.out.println(cust);
		/* addPersonToDatabase(cust1, file);
		 loadPersonDatabase(database, file);
		 addPersonToDatabase(cust2, file);
		 loadPersonDatabase(database, file);
		 addPersonToDatabase(cust3, file);
		 loadPersonDatabase(database, file);
		 addPersonToDatabase(emp1, file);
		 loadPersonDatabase(database, file);
		 addPersonToDatabase(emp2, file);
		 loadPersonDatabase(database, file);
		 addPersonToDatabase(emp3, file);
		 loadPersonDatabase(database, file);*/

	}

	public static void loadPersonDatabase(Map<String, Person> db, File file) {
		Scanner input = null;
		boolean success = false;
		while (success != true) {
			try {
				input = new Scanner(file); // opens scanner
				success = true;
			} catch (FileNotFoundException fnfe) { // checks if file does not exist
				createDatabase(file);
			}
		}
		while (input.hasNext()) {
			String line = input.nextLine();
			String[] ln = line.split(":", 0); // splits line by commas and stores into
			for (String s : ln) {
				//System.out.println(s + "\n");
			}
			if(ln.length == 6) {
				Customer cust = new Customer(ln[0],ln[1],ln[2],ln[3],ln[4], getNextID(db));
				db.put(ln[5], cust);
				System.out.println("added customer from file to database");
			}else if(ln.length == 10) {
				Employee emp = null;
				if(ln[8].equalsIgnoreCase("True")) {
					emp = new Employee(ln[0],ln[1],ln[2],ln[3],ln[4],ln[5],Double.parseDouble(ln[6]),Double.parseDouble(ln[7]),false,getNextID(db));
				}else {
					emp = new Employee(ln[0],ln[1],ln[2],ln[3],ln[4],ln[5],Double.parseDouble(ln[6]),Double.parseDouble(ln[7]),true,getNextID(db));
				}
				if(emp != null) {
					db.put(ln[9], emp);
				}
				System.out.println("added employee from file to database");
			}
		}
		if (input != null) {
			input.close(); // closes scanner if opened
		}
	}
	public static String getNextID(Map<String, Person> PersonDb) {
		String ID = "";
		Set<String> keyset = PersonDb.keySet();
		Iterator<String> it = keyset.iterator();
	     while(it.hasNext()){
	        ID = it.next();
	     }
	     if(ID != "") {
	    	 return ID;
	     }else {
	    	 return "99999";
	     }
	}

	public static void createDatabase(File f) {
		try {
			FileWriter dataWriter = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addPersonToDatabase(Person p, File file) {
		try (FileWriter f = new FileWriter(file, true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter pw = new PrintWriter(b);) {
			pw.println(p.toString());

		} catch (IOException i) {
			System.out.println("IO Exception");
		}

	}
}
