import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Orders {
	static File orderQueue = new File("Program_Files\\orderQueue.txt");

	// Primary Map for holding Customer orders
	static private Map<Integer, ArrayList<Pizza>> orderMap = new LinkedHashMap<>();
	static private FileWriter orderWriter;

	/**
	 * Adds an orderNumber and list of pizzas as an Entry to the orderMap. Also adds
	 * the order to the orderQueue file.
	 * 
	 * @param orderNumber - The order number identified with that particular order.
	 * @param pizzaList   - List of Pizza objects associated with the order.
	 * @throws orderNotFoundException
	 * @throws IOException
	 */
	static public void addCompleteOrder(Integer orderNumber, ArrayList<Pizza> pizzaList)
			throws IOException, OrderNotFoundException {
		checkForOrderFileStatus();
		if (!orderMap.containsKey(orderNumber)) {
			orderMap.put(orderNumber, pizzaList);
			addOrderToFile(orderNumber);
		} else
			System.out.println("Order #" + orderNumber + " already exists.");
	}

	/**
	 * Adds an orderNumber and list of pizzas as an Entry to the orderMap.
	 * 
	 * @param orderNumber - The order number identified with that particular order.
	 * @param pizzaList   - List of Pizza objects associated with the order.
	 * @throws orderNotFoundException
	 * @throws IOException
	 */
	static public void removeOrder(Integer orderNumber) throws IOException, OrderNotFoundException {
	 if(checkForOrderFileStatus().equals("correct")) {
		 if (orderMap.containsKey(orderNumber))
				orderMap.remove(orderNumber);
			else
				throw new OrderNotFoundException(orderNumber);
	 }else 
		 System.out.println("Order file is empty or didn't exist previously");
		
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
	static public Map.Entry<Integer, ArrayList<Pizza>> getOrder(int orderNumber) throws OrderNotFoundException, IOException {
		checkForOrderFileStatus();
			for (Map.Entry<Integer, ArrayList<Pizza>> order : orderMap.entrySet()) {
				if (order.getKey() == orderNumber)
					return order;
			}
			throw new OrderNotFoundException(orderNumber);
	}

	/**
	 * Adds a pizza to a specific order in orderMap.
	 * 
	 * @param orderNumber - The order number identified with that particular order.
	 * @param addedPizza  - The pizza to be added to an order.
	 * @throws IOException
	 * @throws orderNotFoundException - Is thrown if there is no order that has the
	 *                                has the matching orderNumber that is being
	 *                                searched for.
	 */
	static public void addPizzaToOrder(Integer orderNumber, Pizza addedPizza)
			throws OrderNotFoundException, IOException {
		if (checkForOrderFileStatus().equals("correct")) {
			if (orderMap.containsKey(orderNumber))
				orderMap.get(orderNumber).add(addedPizza);
			else
				throw new OrderNotFoundException(orderNumber);
		} else
			System.out.println("Order file is empty or didn't exist previously.");
	}

	/**
	 * Removes a pizza from a specific order in orderMap.
	 * 
	 * @param orderNumber - The order number identified with that particular order.
	 * @param addedPizza  - The pizza to be removed from an order.
	 * @throws IOException
	 * @throws orderNotFoundException - Is thrown if there is no order that has the
	 *                                has the matching orderNumber that is being
	 *                                searched for.
	 */
	static public void removePizzaFromOrder(Integer orderNumber, Pizza removedPizza)
			throws OrderNotFoundException, IOException {
		if (checkForOrderFileStatus().equals("correct")) {
			if (orderMap.containsKey(orderNumber))
				orderMap.get(orderNumber).remove(removedPizza);
			else
				throw new OrderNotFoundException(orderNumber);
		} else
			System.out.println("Order file is empty or didn't exist previously.");
	}

	/**
	 * Writes an order into the orderQueue.txt file.
	 * 
	 * @param order - The order to be added to the file.
	 * @throws IOException
	 * @throws orderNotFoundException
	 */
	static public void addOrderToFile(int orderNum) throws IOException, OrderNotFoundException {
		checkForOrderFileStatus();
		Entry<Integer, ArrayList<Pizza>> tempOrder = getOrder(orderNum);

		orderWriter = new FileWriter("Program_Files\\orderQueue.txt", true);
		orderWriter.write("Order of the day: " + generateOfDayOrderNum() + ", Customer ID: " + tempOrder.getKey()
				+ ", Total Price $" + getOrderTotalCost(tempOrder) + ", Pizzas: ");
		for (Pizza pizza : tempOrder.getValue()) {
			orderWriter.write(pizza.getPizzaName() + ", ");
		}
		orderWriter.write("\n");
		orderWriter.close();
	}

	/**
	 * COME BACK IF TIME
	 * 
	 * @param orderNumber
	 * @throws IOException
	 * @throws OrderNotFoundException
	 */
	static public void removeOrderFromFile(int orderNumber) throws IOException, OrderNotFoundException {
		int count = 0;

		if (!checkForOrderFileStatus().equals("correct")) {
			System.out.println("Order file didn't exist previously or is empty.");
			return;
		}

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
			throw new OrderNotFoundException(orderNumber);
		}

		clearOrderFile();
		orderWriter = new FileWriter(orderQueue);
		for (String order : allLines) {
			orderWriter.write(order + "\n");
		}
		orderWriter.close();
	}

	/**
	 * Searches to see if the the orderQueue.txt file exists. If not, a new file is
	 * created.
	 * 
	 * @return - (True) If the file is found, otherwise (False).
	 * @throws IOException
	 */
	static public String checkForOrderFileStatus() throws IOException {
		if (!orderQueue.exists()) {
			orderQueue = new File("Program_Files\\orderQueue.txt");
			orderQueue.createNewFile();
			return "didntExist";
		} else if (orderQueue.length() == 0)
			return "isEmpty";
		else
			return "correct";
	}

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
	 * Creates a number which is the placement of the newest order in the
	 * orderQueue. EX: if there are five orders that happened previously in the day,
	 * then generateOfDayOrderNum would return 6.
	 * 
	 * @return The new "of day" order number.
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 */
	static public int generateOfDayOrderNum() throws NumberFormatException, FileNotFoundException, IOException {
		checkForOrderFileStatus();

		Scanner reader = new Scanner(orderQueue);

		if (orderQueue.length() == 0) {
			reader.close();
			return 1;
		}
		String line = reader.nextLine();
		while (reader.hasNext())
			line = reader.nextLine();

		List<String> orderInformation = new ArrayList<String>(Arrays.asList(line.split(",")));
		reader.close();
		return Integer.parseInt(String.valueOf(orderInformation.get(0).substring(18, orderInformation.get(0).length())))
				+ 1;
	}

	/**
	 * Tallies up and returns the total cost of all Pizzas in an order.
	 * 
	 * @return - Total cost of all Pizzas on the order.
	 */
	static public double getOrderTotalCost(Map.Entry<Integer, ArrayList<Pizza>> order) {
		double totalCost = 0;
		for (Pizza pizza : order.getValue()) {
			totalCost += pizza.getPrice();
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
	static public void printOrderRecipt(int orderNumber) throws IOException, OrderNotFoundException {
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

	// Save Method In Case it's needed

}

//To Do: Figure out how to handle exception? 
class OrderNotFoundException extends Exception {
	public OrderNotFoundException(int orderNumber) {
		super("Order: " + orderNumber + ", could not be found.");
	}
}
