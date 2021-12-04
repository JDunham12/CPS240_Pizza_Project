import java.util.ArrayList;
import java.util.List;

public class Pizza {

	private double totalPrice;
	private int pizzaNumber;
	private List<String> pizzaToppings;

	// Default Constructor
	public Pizza() {
		pizzaToppings = new ArrayList<String>();
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getPizzaNumber() {
		return pizzaNumber;
	}

	public void setPizzaNumber(int pizzaNumber) {
		this.pizzaNumber = pizzaNumber;
	}

	// Constructor w/ all parameters except toping list
	public Pizza(int pizzaNumber, List<String> pizzaToppings) {
		this.pizzaNumber = pizzaNumber;
		this.pizzaToppings = pizzaToppings;
		this.totalPrice = calculateTotalPrice(pizzaToppings);
	}

	public void addTopping(String topping) {
		this.pizzaToppings.add(topping);
	}

	public void addListOfToppings(List<String> toppings) {
		this.pizzaToppings.addAll(toppings);
	}
	
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
