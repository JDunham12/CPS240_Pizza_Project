import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Orders {
	static File orderQueue = new File("Program_Files\\orderQueue.txt");

	// Primary Map for holding Customer orders
	static private Map<String, List<Pizza>> orderMap = new LinkedHashMap<>();
	static private FileWriter orderWriter;
	
	/**
	 * Places all orders from the orderQueue, into the orderMap. 
	 * @throws FileNotFoundException 
	 */
	static public void loadOrders() throws FileNotFoundException {
		Scanner reader = new Scanner(orderQueue);
		List<String> allLines = new ArrayList<String>();

		while (reader.hasNext()) {
			String line = reader.nextLine();
			List<String> orderInformation = new ArrayList<String>(Arrays.asList(line.split(":")));
			String customerID = orderInformation.get(0).substring(orderInformation.get(0).indexOf(":") + 2,
					orderInformation.get(0).length());
			String pizzas = orderInformation.get(4);
			System.out.println(customerID + " " + pizzas);
		}
	}

	/**
	 * Adds an orderNumber and list of pizzas as an Entry to the orderMap. Also adds
	 * the order to the orderQueue file.
	 * 
	 * @param orderNumber - The order number identified with that particular order.
	 * @param pizzaList   - List of Pizza objects associated with the order.
	 * @throws IOException
	 */
	static public void addCompleteOrder(String customerID, List<Pizza> pizzaList) throws IOException {
		Entry<String, List<Pizza>> tempOrder = Map.entry(customerID,pizzaList);
		
		if(orderMap.containsKey(customerID))
			orderMap.remove(customerID);
		
		orderMap.put(customerID, pizzaList);
		addOrderToFile(tempOrder);
		
	}

	/**
	 * Removes an orderNumber and list of pizzas Entry from the orderMap.
	 * 
	 * @param orderNumber - The order number identified with that particular order.
	 * @param pizzaList   - List of Pizza objects associated with the order.
	 * @throws orderNotFoundException
	 * @throws IOException
	 */
	static public void removeOrder(String orderNumber){
		 if (orderMap.containsKey(orderNumber))
				orderMap.remove(orderNumber);
	}

	/**
	 * Searches for and returns a requested order based on the given order number.
	 * 
	 * @param orderNumber - The order number that is being searched for.
	 * @return The Map.Entry of the specific order.
	 * @throws IOException 
	 * @throws orderNotFoundException - Is thrown if there is no order that has the
	 *                                has the matching orderNumber that is being
	 *                                searched for.
	 */
	static public Map.Entry<String, List<Pizza>> getOrder(String customerID) {
			for (Map.Entry<String, List<Pizza>> order : orderMap.entrySet()) {
				if (order.getKey().equals(customerID))
					return order;
			}
			return null;
	}

	/**
	 * Writes an order into the orderQueue.txt file.
	 * 
	 * @param order - The order to be added to the file.
	 * @throws IOException
	 * @throws orderNotFoundException
	 */
	static public void addOrderToFile(Entry<String, List<Pizza>> newOrder) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH-mm-ss");
		Date orderDate = new Date();
		
		checkForOrderFileStatus();

		orderWriter = new FileWriter("Program_Files\\orderQueue.txt", true);
		orderWriter.write("Customer ID =" + newOrder.getKey() + ":Date and Time of Order =" + formatter.format(orderDate)
				+ ":Total Price =" + getOrderTotalCost(newOrder) + ":Number of Pizzas =" + newOrder.getValue().size() + ": Pizzas =");
		for (Pizza pizza : newOrder.getValue()) {
			orderWriter.write(pizza.toString() + ": ");
		}
		orderWriter.write("\n");
		orderWriter.close();
	}
	

	/**
	 * Removes a 
	 * 
	 * @param orderNumber
	 * @throws IOException
	 * @throws OrderNotFoundException
	 */
	static public void removeOrderFromFile(int orderNumber) throws IOException {
		int count = 0;
		
		Scanner reader = new Scanner(orderQueue);
		List<String> allLines = new ArrayList<String>();

		while (reader.hasNext()) {
			count++;
			String line = reader.nextLine();
			List<String> orderInformation = new ArrayList<String>(Arrays.asList(line.split(",")));
			String customerID = orderInformation.get(1).substring(orderInformation.get(1).indexOf(":") + 2,
					orderInformation.get(1).length());
			if (!customerID.equals(String.valueOf(orderNumber)))
				allLines.add(line);
		}

		if (allLines.size() == count) {
			
		}

		clearOrderFile();
		
		orderWriter = new FileWriter(orderQueue);
		for (String order : allLines) {
			orderWriter.write(order + "\n");
		}
		orderWriter.close();
	}

	//Place in MAIN
	/**
	 * Searches to see if the the orderQueue.txt file exists. If not, a new file is
	 * created.
	 * 
	 * @return - (True) If the file is found, otherwise (False).
	 * @throws IOException
	 */
	static public void checkForOrderFileStatus() throws IOException {
		if (!orderQueue.exists()) {
			orderQueue = new File("Program_Files\\orderQueue.txt");
			orderQueue.createNewFile();
		}
	}

	/**
	 * Clears all orders from the orderQueue file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	static public void clearOrderFile() throws FileNotFoundException, IOException {
		new FileOutputStream("Program_Files\\orderQueue.txt").close();
	}
	
	/**
	 * Tallies up and returns the total cost of all Pizzas in an order.
	 * @return - Total cost of all Pizzas on the order.
	 */
	static public double getOrderTotalCost(Map.Entry<String, List<Pizza>> order) {
		double totalCost = 0;
		for (Pizza pizza : order.getValue()) {
			totalCost += pizza.getTotalPrice();
		}
		return totalCost;
	}

	// To Do: Touchup output to receipt files
	/**
	 * Creates a customer receipt of a particular order in the form of a text file,
	 * within the "Receipts" folder.
	 * 
	 * @param orderNumber - The order number that is being searched for.
	 * @throws IOException
	 * @throws orderNotFoundException - Is thrown if there is no order that has the
	 *                                has the matching orderNumber that is being
	 *                                searched for.
	 */
	static public void printOrderRecipt(Map.Entry<String, List<Pizza>> order) throws IOException {
			String filePath = "Receipts\\Customer_Order_#" + order.getKey() + "_receipt.txt";
			
			orderWriter = new FileWriter(filePath);
			orderWriter.write("*************************************************************************\n"
					+ "Order Number: " + order.getKey() + "\n"
					+ "-------------------------------------------------------------------------\n");
			for (Pizza pizza : order.getValue())
				orderWriter.write(pizza.toString() + "\n");
			orderWriter.write("----------------------------------------------------------------------\n");
			orderWriter.write("Total Cost: $" + getOrderTotalCost(order) + "\n");
			orderWriter.write("*************************************************************************\n");
			orderWriter.close();
	}
}

