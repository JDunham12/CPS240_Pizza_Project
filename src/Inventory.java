import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Inventory {
	
	static private ArrayList<String> defaultToppings = new ArrayList<String>();
	static File inventoryDB = new File("Program_Files\\inventoryDB.txt");
	
	
	static public void loadDefaultValues() {
	}
	
	/**
	 * Searches to see if the the orderQueue.txt file exists. If not, a new file is
	 * created.
	 * 
	 * @return - (True) If the file is found, otherwise (False).
	 * @throws IOException
	 */
	static public boolean checkForInventoryFile() throws IOException {
		if (inventoryDB.exists()) {
			return true;
		} else {
			inventoryDB = new File("Program_Files\\inventoryDB.txt");
			inventoryDB.createNewFile();
			return false;
		}
	}

	/**
	 * Searches to see if the the orderQueue.txt file exists. If not, a new file is
	 * created.
	 * 
	 * @return - (True) If the file is found, otherwise (False).
	 * @throws IOException
	 */
	static public boolean checkForOrderFileStatus() throws IOException {
		if (!inventoryDB.exists()) {
			inventoryDB = new File("Program_Files\\inventoryDB.txt");
			inventoryDB.createNewFile();
			return false;
		} else if (inventoryDB.length() == 0)
			return false;
		else
			return true;
	}
}
