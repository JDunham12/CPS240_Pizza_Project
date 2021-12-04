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

import javafx.util.Pair;

/**
 * The Orders class is primarily used to contain, manipulate, and display any orders for the program. 
 */
public class Orders {
	static File orderQueue = new File("Program_Files\\orderQueue.txt");

	// Primary Map for holding Customer orders 
	// Key : Pair<Integer, String> -> Pair<orderNumber,customerID>
	// Value : List<Pizza> -> List of Pizzas for an order
	static private Map<Pair<Integer,String>, List<Pizza>> orderMap = new LinkedHashMap<>();
	static private FileWriter orderWriter;

	/**
	 * Adds an orderNumber and list of pizzas as an Entry to the orderMap.
	 * 
	 * @param customerPair - (Key) The orderNumber and customerID Pair<Integer,String>
	 * @param pizzaList   - (Value) List of Pizza objects associated with the order.
	 */
	static public void addCompleteOrder(Pair<Integer,String> customerPair, List<Pizza> pizzaList) {		
		orderMap.put(customerPair, pizzaList);
	}

	/**
	 * Removes an orderNumber and list of pizzas Entry from the orderMap.
	 * 
	 * @param customerPair - The orderNumber and customerID Pair<Integer,String>.
	 */
	static public void removeOrder(Pair<Integer,String> customerPair){
		 if (orderMap.containsKey(customerPair))
				orderMap.remove(customerPair);
	}

	/**
	 * Searches for and returns a requested order based on the given orderNumber and customerID Pair<Integer,String>.
	 * 
	 * @param customerPair - The orderNumber and customerID Pair.
	 * @return The Map.Entry of the specific order.
	 */
	static public Map.Entry<Pair<Integer,String>, List<Pizza>> getOrder(Pair<Integer,String> customerPair) {
			//Searching or order based on the orderNumber and customerID pair
			for (Map.Entry<Pair<Integer,String>, List<Pizza>> order : orderMap.entrySet()) {
				if (order.getKey().getKey() == customerPair.getKey())
					return order;
			}
			//Null if Order cannot be found
			return null;
	}
	
	/**
	 * Tallies up and returns the total cost of all Pizzas in an order.
	 * @return - Total cost of all Pizzas on the order.
	 */
	static public double getOrderTotalCost(Map.Entry<Pair<Integer,String>, List<Pizza>> order) {
		double totalCost = 0;
		for (Pizza pizza : order.getValue()) {
			totalCost += pizza.getTotalPrice();
		}
		return totalCost;
	}
	
	/**
	 * Generates a random number to assign with an order. 
	 * @param customerID - The current User's Customer ID
	 * @return A random number between 100 and 900
	 */
	static public int generateOrderNumber(String customerID) {
		boolean randNumFound = false;
		int orderNum = 0;
		
		//729 possible number combinations between 100 and 900
		int numberRange = 729;
		int count = 0;
		
		//If orderMap is empty
		if(orderMap.size() == 0) {
			return (int) (Math.random() * (900 - 100) + 100);
		}else {
			while(!randNumFound){
				count++;
				orderNum = (int) (Math.random() * (900 - 100) + 100);
				if(!orderMap.keySet().contains(new Pair<Integer,String>(orderNum, customerID)))
					randNumFound = true;
				//If number of iterations exceed the amount of possible numbers, then 0 will be returned
				if(count > numberRange)
					return 0;
				}
		}
		return orderNum;
	}
	
	/**
	 * Creates a list of the chosen user's current orders in the orderMap. Used when selecting which orders
	 * to be removed from a user's account. 
	 * @param customerID - The current User's Customer ID
	 * @return - List of current orders based on ID 
	 */
	static public String printCurrentUserOrders(String customerID) {
		String finalString = "";
		for(Map.Entry<Pair<Integer, String>, List<Pizza>> order : orderMap.entrySet()) {
			//If the customerID matches the ID of an order, add that order to the string. 
			if(order.getKey().getValue().equals(customerID)) {
				finalString +="Order Number: " + order.getKey().getKey() + "\n";
				for(Pizza pizzaObj : order.getValue()) {
					finalString += "- " + pizzaObj.toString() + "\n";
				}
			finalString += "\n";
			}
		}
		return finalString;
	}

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
	static public String printOrderRecipt(Pair<Integer,String> customerPair, List<Pizza> pizzaList) throws IOException {
			String finalString = "";
			Entry<Pair<Integer,String>, List<Pizza>> newOrder = Map.entry(customerPair, pizzaList);

			finalString +=("*************************************************************************\n"
					+ "Order Number: " + customerPair.getKey() + "\n"
					+ "-------------------------------------------------------------------------\n");
			for (Pizza pizza : pizzaList)
				finalString += (pizza.toString() + "\n");
			finalString +=("----------------------------------------------------------------------\n");
			finalString +=("Total Cost: $" + getOrderTotalCost(newOrder) + "\n");
			finalString +=("*************************************************************************\n");
			return finalString;
	}
	
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
	/*
	static public void printOrderRecipt(Map.Entry<Pair<Integer,String>, List<Pizza>> order) throws IOException {
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
	
	//Ununtilized Code
	
	/**
	 * Searches to see if the the orderQueue.txt file exists. If not, a new file is
	 * created.
	 * 
	 * @return - (True) If the file is found, otherwise (False).
	 * @throws IOException
	 */
	/*
	static public void checkForOrderFileStatus() throws IOException {
		if (!orderQueue.exists()) {
			orderQueue = new File("Program_Files\\orderQueue.txt");
			orderQueue.createNewFile();
		}
	}
	*/
	
	/**
	 * Clears all orders from the orderQueue file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	/*
	static public void clearOrderFile() throws FileNotFoundException, IOException {
		new FileOutputStream("Program_Files\\orderQueue.txt").close();
	}
	*/
	
	/**
	 * Writes an order into the orderQueue.txt file.
	 * 
	 * @param order - The order to be added to the file.
	 * @throws IOException
	 * @throws orderNotFoundException
	 */
	/*
	static public void addOrderToFile(Entry<Integer, List<Pizza>> newOrder) throws IOException {
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
	*/

	/**
	 * Removes a 
	 * 
	 * @param orderNumber
	 * @throws IOException
	 * @throws OrderNotFoundException
	 */
	/*
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
	*/
}

