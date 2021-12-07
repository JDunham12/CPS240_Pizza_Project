import java.util.ArrayList;
import java.util.List;

public class Pizza {

	private double totalPrice;
	private int pizzaNumber;
	private List<String> pizzaToppings;

	// Default Constructor
	public Pizza() {
		this.pizzaNumber = 0;
		this.pizzaToppings = new ArrayList<String>();
		this.totalPrice = 0;
	}
	
	// Constructor w/ Parameters
	public Pizza(int pizzaNumber, List<String> pizzaToppings) {
		this.pizzaNumber = pizzaNumber;
		this.pizzaToppings = pizzaToppings;
		this.totalPrice = calculateTotalPrice(pizzaToppings);
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(List<String> pizzaToppings) {
		this.totalPrice = calculateTotalPrice(pizzaToppings);
	}

	public int getPizzaNumber() {
		return pizzaNumber;
	}

	public void setPizzaNumber(int pizzaNumber) {
		this.pizzaNumber = pizzaNumber;
	}

	public List<String> getPizzaToppings() {
		return pizzaToppings;
	}

	public void setPizzaToppings(List<String> pizzaToppings) {
		this.pizzaToppings = pizzaToppings;
	}

	/**
	 * Calculates the total cost of a Pizza based on the number of toppings in the pizzaToppings List.
	 * The base price is $10, Cheese is free, and every other topping adds $0.50 to the cost. 
	 * @param newPizzaToppings - The List of pizzaToppings to be accounted for.
	 * @return The total cost of a pizza in dollars
	 */
	public double calculateTotalPrice(List<String> newPizzaToppings) {
		//Base price of $10
		double pizzaPrice = 10;
		//Add 50 cents per topping
		for(String topping: newPizzaToppings) {
			pizzaPrice = (!topping.equals("Cheese")) ? (pizzaPrice + 0.5)  : pizzaPrice ;
		}
		return pizzaPrice;		
	}

	@Override
	public String toString() {
		String finalString = "Pizza Number: " + this.pizzaNumber + ", Price: $" + this.totalPrice + ", Toppings: ";
		for(String topping : pizzaToppings)
			finalString += topping + ", "; 
		return finalString;
	}
}
