import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
		Pizza test1 = new Pizza("t1", 14, 4.5, "Thicc", "The goood stuff");
		Pizza test2 = new Pizza("t2", 16, 7.8, "Deep", "Ranch me malato");
		Pizza test3 = new Pizza("t3", 34, 50.9, "Thin", "Oyster Sauce");
		
		ArrayList<Pizza> bobsOrder = new ArrayList<Pizza>();
		bobsOrder.add(test1);
		bobsOrder.add(test2);
		bobsOrder.add(test3);
		
		Person bob = new Customer("bob", "bobTheBuilder", "%KOP*", "12232 The Cool Place", "34343434", "25364");
		
		Entry<Integer, ArrayList<Pizza>> entry = Map.entry(2323,  bobsOrder);
		Entry<Integer, ArrayList<Pizza>> entry2 = Map.entry(2323,  bobsOrder);
		
		Orders.addCompleteOrder(2323, bobsOrder);
		try {
			Orders.printOrderRecipt(2323);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Orders.addOrderToFile(entry);
			Orders.addOrderToFile(entry2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
