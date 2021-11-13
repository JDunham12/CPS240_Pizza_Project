import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
	
	Person orderPurchaser;
	Map<Pizza,Integer> orderList;
	
	//Default Constructor
	public Order() {
		orderPurchaser = new Person();
		orderList = new LinkedHashMap<Pizza, Integer>();
	}
	
	//Constructor w/ only orderPurchaser
	public Order(Person orderPurchaser) {
		this.orderPurchaser = orderPurchaser;
		this.orderList = new LinkedHashMap<Pizza, Integer>();
	}
	
	//Constructor w/ Parameters
	public Order(Person orderPurchaser, Map<Pizza, Integer> orderList) {
		this.orderPurchaser = orderPurchaser;
		this.orderList = orderList;
	}

	
	public Person getOrderPurchaser() {
		return orderPurchaser;
	}

	public void setOrderPurchaser(Person orderPurchaser) {
		this.orderPurchaser = orderPurchaser;
	}

	public Map<Pizza, Integer> getOrderList() {
		return orderList;
	}
	
	public void addPizzaToOrder(Pizza tempPizza) {
		if(orderList.isEmpty()) {
			orderList.put(tempPizza, 1);
			return;
		}
			
		for(Map.Entry<Pizza, Integer> pizzaEntry: orderList.entrySet()) {
			if(pizzaEntry.getKey().equals(tempPizza)) {
				pizzaEntry.setValue(pizzaEntry.getValue()+1);
			}else{
				orderList.put(tempPizza, 1);
			}
		}
	}
	
	//What if a pizza isn't found?
	public void removePizzaFromOrder(Pizza tempPizza) {
		for(Map.Entry<Pizza, Integer> pizzaEntry: orderList.entrySet()) {
			if(pizzaEntry.getKey().equals(tempPizza)) {
				orderList.remove(pizzaEntry);
			}
		}
	}
	
	//Adds a pre-defined Map<Pizza,Integer> to orderList
	public void setOrderList(Map<Pizza, Integer> orderList) {
		this.orderList = orderList;
	}
	
	public String printOrderList() {
		String finalString = "Customer: " + orderPurchaser.getName()+"\n";
		for(Map.Entry<Pizza, Integer> pizzaEntry : orderList.entrySet()) {
			finalString += pizzaEntry.getKey().getPizzaName() + " : " + pizzaEntry.getValue() + "\n";
		}
		return finalString;
	}
	
	
	
	
}
