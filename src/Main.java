import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//test
public class Main{
	public static void main(String[] args) {
		Map<String, List<Person>> database = new LinkedHashMap<String, List<Person>>();
		loadDatabase(database);
		Customer cust = new Customer();
		
		System.out.println(cust);
		System.out.println("Hello World!");
		addPersonToDatabase(cust);
	}
	public static void loadDatabase(Map<String, List<Person>> db) {
		File file = new File("PersonData.txt");
		Scanner input = null;
		try {
			input = new Scanner(file); // opens scanner
		} catch (FileNotFoundException fnfe) { // checks if file does not exist
			System.out.println("File Not Found " + fnfe); // displays error
			System.exit(0); // exists nicely
		}

		String[] ln; // string array used to hold data from line being split from publicationData.txt

		while (input.hasNext()) { // runs until file does not have next line
			// used to display numbered sections of each line from file for testing
			// for (int i = 0; i < ln.length; i++) {
			// System.out.print(" |" + (i) + "| " + ln[i]);
			// }
			// System.out.println(""); //

			ln = input.nextLine().split(",", 0); // splits line by commas and stores into ln array
			// ln array is used to hold the data for current line
			if(db.get(ln[0]) == null) {
				db.put(null, null);
			}

		}
		if (input != null) {
			input.close(); // closes scanner if opened
		}
	}
	public static void addPersonToDatabase(Person p) {
		File file = new File("PersonData.txt");
		PrintWriter pw = null;
		try {
			if(file.exists() !=true) {
				pw = new PrintWriter("PersonData.txt", "UTF-8");
			}
		}catch(UnsupportedEncodingException uee) {
			System.out.println("UTF-8 does not recognize this data type");
			System.exit(1);
		}catch(FileNotFoundException fnfe) {
			System.out.println("That file does not exist");
			System.exit(1);
		}
	}
}