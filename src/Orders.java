import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Orders {
	static File orderQueue = new File("Program_Files\\orderQueue.txt");

	// Primary Map for holding Customer orders
	static private Map<Integer, ArrayList<Pizza>> orderMap = new LinkedHashMap<>();
	static private int orderCount = 0;
	static private FileWriter orderWriter;

	/**
	 * Adds a complete order Entry to the orderMap.
	 * 
	 * @param orderNumber - The order number identified with that particular order.
	 * @param pizzaList   - List of Pizza objects associated with the order.
	 */
	static public void addCompleteOrder(Integer orderNumber, ArrayList<Pizza> pizzaList) {
		orderMap.put(orderNumber, pizzaList);
	}

	// What if order isn't found???
	// Add a pizza to a specific order
	/**
	 * 
	 * @param orderNumber
	 * @param addedPizza
	 */
	static public void addPizzaToOrder(Integer orderNumber, Pizza addedPizza) {
		getOrder(orderNumber).getValue().add(addedPizza);
	}

	/**
	 * 
	 * @param order
	 * @throws IOException
	 */
	static public void addOrderToFile(Map.Entry<Integer, ArrayList<Pizza>> order) throws IOException {
		if (checkForOrderFile()) {
			orderWriter = new FileWriter("Program_Files\\orderQueue.txt", true);
			orderWriter.write("Order of the day: " + generateOrderNumber() + ", Customer ID: " + order.getKey()
					+ ", Total Price: $" + getOrderTotalCost(order) + ", Pizzas: ");
			for (Pizza pizza : order.getValue()) {
				orderWriter.write(pizza.getPizzaName() + ", ");
			}
			orderWriter.write("\n");
			orderWriter.close();
		}
	}

	/**
	 * 
	 * @param orderNumber
	 */
	static public void removeOrderFromFile(int orderNumber) {
		
		//orderWriter = new PrintWriter();

		///BufferedReader b1 = new BufferedReader(new FileReader("Program_Files\\orderQueue.txt"));
		
		//String line1 = br1.
	}

	// One method to check for all folders/files????
	// Handle possible exceptions!!!
	/**
	 * Searches to see if the the orderQueue.txt file exists. If not, a new file is
	 * created.
	 * 
	 * @return - (True) If the file is found, otherwise (False).
	 * @throws IOException
	 */
	static public boolean checkForOrderFile() throws IOException {
		if (orderQueue.exists()) {
			return true;
		} else {
			orderQueue = new File("Program_Files\\orderQueue.txt");
			orderQueue.createNewFile();
			return false;
		}
	}

	// Handle possible exceptions!!
	/**
	 * Searches for and returns a requested order based on the given order number.
	 * 
	 * @param orderNumber - The order number that is being searched for.
	 * @return The Map.Entry of the specific order.
	 */
	static public Map.Entry<Integer, ArrayList<Pizza>> getOrder(int orderNumber) {
		for (Map.Entry<Integer, ArrayList<Pizza>> order : orderMap.entrySet()) {
			if (order.getKey() == orderNumber)
				return order;
		}
		return null;
	}

	// Handle possible exceptions!!!
	/**
	 * Clears all orders from the orderQueue file.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	static public void clearOrderFile() throws FileNotFoundException, IOException {
		new FileOutputStream("Program_Files\\orderQueue.txt").close();
	}

	/**
	 * Creates an order number to be assigned to an order. Additionally, increments
	 * orderCount for every order number generated.
	 * 
	 * @return The new order number.
	 */
	static public int generateOrderNumber() {
		orderCount++;
		return orderCount;
	}

	/**
	 * Tallies up and returns the total cost of all Pizzas in an order.
	 * 
	 * @return
	 */
	static public double getOrderTotalCost(Map.Entry<Integer, ArrayList<Pizza>> order) {
		double totalCost = 0;
		for (Pizza pizza : order.getValue()) {
			totalCost += pizza.getPrice();
		}
		return totalCost;
	}

	// Handle possible exceptions!!
	/**
	 * Creates a customer receipt of a particular order in the form of a text file,
	 * within the "Receipts" folder.
	 * 
	 * @param orderNumber - The order number that is being searched for.
	 * @throws IOException
	 */
	static public void printOrderRecipt(int orderNumber) throws IOException {
		Map.Entry<Integer, ArrayList<Pizza>> tempOrder = getOrder(orderNumber);
		if (tempOrder != null) {
			String filePath = "Receipts\\Order_#" + orderNumber + "_receipt.txt";
			orderWriter = new FileWriter(filePath);
			orderWriter.write("*************************************************************************\n"
					+ "Order Number: " + tempOrder.getKey() + "\n"
					+ "-------------------------------------------------------------------------\n");
			for (Pizza pizza : tempOrder.getValue())
				orderWriter.write(pizza.toString() + "\n");
			orderWriter.write("----------------------------------------------------------------------\n");
			orderWriter.write("Total Cost: $" + getOrderTotalCost(tempOrder) + "\n");
			orderWriter.write("*************************************************************************\n");
			orderWriter.close();
		}
	}
}
