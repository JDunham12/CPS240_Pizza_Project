import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//test
public class Main {
	public static void main(String[] args) {
		Map<String, List<Person>> database = new LinkedHashMap<String, List<Person>>();
		File file = new File("Program_Files\\PersonData.txt");
		loadDatabase(database, file);
		Customer cust1 = new Customer("John Smith", "JSmith78", "Password", "p sherman 42, wallaby way sydney",
				"1234567890");
		Customer cust2 = new Customer("Joe Shmoe", "xXJShmoeXx", "password", "p sherman 43 wallaby way sydney",
				"1891230101");
		Customer cust3 = new Customer("John Smith", "JSmith78", "P@ssword", "The moon", "8115552022");
		// System.out.println(cust);
		//addPersonToDatabase(cust1, file);
		//addPersonToDatabase(cust2, file);
		//addPersonToDatabase(cust3, file);

	}

	public static void loadDatabase(Map<String, List<Person>> db, File file) {
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

		String[] ln;

		while (input.hasNext()) { // runs until file does not have next line
			// used to display numbered sections of each line from file for testing
			//for (int i = 0; i < ln.length; i++) {
			//System.out.print(" |" + (i) + "| " + ln[i]);
			//}
			System.out.println(input.nextLine()); //

			// ln = input.nextLine().split(",", 0); // splits line by commas and stores into
			// ln array
			// ln array is used to hold the data for current line
			// if (db.get(ln[0]) == null) {
			// db.put(null, null);
			// }

		}
		// if (input != null) {
		// input.close(); // closes scanner if opened
		// }
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
