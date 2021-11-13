import java.util.List;

public class Pizza {

	private double price;
	private int size;
	private String name;
	private String crust;
	private String sauce;
	private String cheese;
	private List<String> toppings;

	// Default Constructor
	public Pizza() {

	}

	// Constructor w/ Parameters
	public Pizza(String name, int size, double price, String crust, String sauce, String cheese,
			List<String> toppings) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.crust = crust;
		this.sauce = sauce;
		this.cheese = cheese;
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
	public String toString() {
		return "Name= " + name + ", Size= " + this.size + "Price= " + this.price + ", Crust= " + this.crust
				+ ", Sauce= " + this.sauce + ", Cheese= " + this.cheese + ", Toppings= " + this.toppings;
	}
}
