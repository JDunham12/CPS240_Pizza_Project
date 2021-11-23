import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pizza implements Comparable<Pizza> {

	private double price;
	private int size;
	private String name;
	private String crust;
	private String sauce;
	private List<String> toppings;

	// Default Constructor
	public Pizza() {
		toppings = new ArrayList<String>();
	}

	// Constructor w/ all parameters except toping list
	public Pizza(String name, int size, double price, String crust, String sauce) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.crust = crust;
		this.sauce = sauce;
		this.toppings = new ArrayList<String>();
	}

	// Constructor w/ Parameters
	public Pizza(String name, int size, double price, String crust, String sauce, List<String> toppings) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.crust = crust;
		this.sauce = sauce;
		this.toppings = toppings;
	}

	public void addTopping(String topping) {
		this.toppings.add(topping);
	}

	public void addListOfToppings(List<String> toppings) {
		this.toppings.addAll(toppings);
	}

	public String getPizzaName() {
		return this.name;
	}

	@Override
	public int compareTo(Pizza o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		String finalString = "Pizza: " + this.name + "\n" + "Size : " + this.size + "\n" + "Crust: " + this.crust + "\n"
				+ "Sauce: " + this.sauce + "\n" + "Toppings:\n";
		for (String topping : toppings) {
				finalString += "     - " + topping + "\n";
		}
		finalString += "Price: " + this.price;

		return finalString;
	}
}
