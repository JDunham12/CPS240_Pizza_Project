import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Orders {
	static File orderQueue = new File("Program_Files\\orderQueue.txt");

	// Primary Map for holding Customer orders
	static private Map<Integer, ArrayList<Pizza>> orderMap = new LinkedHashMap<>();

	
	
	// Add a new complete entry to the map
	static public void addCompleteOrder(Integer orderNumber, ArrayList<Pizza> pizzaList) {
		orderMap.put(orderNumber, pizzaList);
	}

	// What if order isn't found???
	// Add a pizza to a specific order
	static public void addPizzaToOrder(Integer orderNumber, Pizza addedPizza) {
		for (Map.Entry<Integer, ArrayList<Pizza>> entry : orderMap.entrySet()) {
			if (entry.getKey() == orderNumber)
				entry.getValue().add(addedPizza);
		}
	}

	// Testing Code (Still in development)
	static public void addOrderToFile(Map.Entry<Integer, ArrayList<Pizza>> order) throws IOException {
		FileWriter orderWriter = new FileWriter("Program_Files\\orderQueue.txt", true);
		orderWriter.write("*************************************************************************\n"
				+ "Order Number: " + order.getKey() + "\n" +
				"-------------------------------------------------------------------------\n");
		for(Pizza pizza : order.getValue())
			orderWriter.write(pizza.toString() + "\n");
		orderWriter.write("*************************************************************************\n");
		orderWriter.close();
	}

	// Checks to see if orderQueue file exists
	static public boolean checkForOrderFile() throws IOException {
		if (orderQueue.exists()) {
			return true;
		} else {
			orderQueue = new File("Program_Files\\orderQueue.txt");
			orderQueue.createNewFile();
			return false;
		}
	}

	// Removes all text from order file
	static public void clearOrderFile() throws FileNotFoundException, IOException {
		new FileOutputStream("Program_Files\\orderQueue.txt").close();
	}

}
