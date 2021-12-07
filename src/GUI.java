import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GUI extends Application {
	Customer cust;
	Employee emp;
	Boolean loginResult = false;
	String[] menuitems = { "Cheese", "Pepperoni", "Green Peppers", "Onions", "Olives", "Sausage", "Ham", "Pineapple",
			"Anchovies", "Chicken" };
	Boolean[] toppingsSelected = { false, false, false, false, false, false, false, false, false, false };
	
	String punchOutTime = "";
	String punchInTime = "";
	TextField tf1;
	TextField tf2;
	boolean CAccess, EAccess = false;
	
	//Pizza Related Variables
	List<CheckBox> toppingList = new ArrayList<CheckBox>();
	List<Pizza> pizzaCart = new ArrayList<Pizza>();
	int pizzaCounter = 0;

	@Override
	public void start(final Stage stage) {

//Sets up the first window to be launched
////////////////////////////////////////////////////////////////////////////////////////////////
		Button loginButton = new Button("Login");
		Button quit = new Button("Exit");
		Button create = new Button("Create new account");
		Button showPunches = new Button("Show Punches");
		Label label = new Label("Welcome!");
		Label label1 = new Label("Username:");
		Label label2 = new Label("Password:");
		Label cancelHeaderLabel = new Label("No orders to be removed");
		Label newacc = new Label("Please create a new username and password");
		tf1 = new TextField(); 
		tf2 = new TextField();
		HBox hbox = new HBox(20, quit, create, loginButton);
		VBox pane = new VBox(10, label1, tf1, label2, tf2, label);
		pane.getChildren().add(hbox);
		tf1.setMaxWidth(240);
		tf2.setMaxWidth(240);
		pane.setAlignment(Pos.CENTER);
		hbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Pizza Project");
		stage.setWidth(300);
		stage.show();

//Sets up Create new account window
////////////////////////////////////////////////////////////////////////////////////////////		
		TextField ctf1 = new TextField(), ctf2 = new TextField(), ctf3 = new TextField(), ctf4 = new TextField(),
				ctf5 = new TextField();
		TextField etf1 = new TextField(), etf2 = new TextField(), etf3 = new TextField(),
				etf4 = new TextField(), etf5 = new TextField(), etf6 = new TextField(),
				etf7 = new TextField();
		Button goBack = new Button("Close");
		Button newEmp = new Button("Create new employee");
		CheckBox cb = new CheckBox();
		Button logreturn = new Button("Return to login");
		Button newlog = new Button("Create and login");
		Label newEmployeeLabel = new Label("Please create a new username and password");
		Label newacc1 = new Label("Username:"), newacc2 = new Label("Password:"), newacc3 = new Label("Name:"),
				newacc4 = new Label("Address:"), newacc5 = new Label("Phone Number:");
		ctf1.setMaxWidth(240);
		ctf2.setMaxWidth(240);
		ctf3.setMaxWidth(240);
		ctf4.setMaxWidth(240);
		ctf5.setMaxWidth(240);
		HBox hcreateLayout = new HBox(40, logreturn, newlog);
		VBox vcreateLayout = new VBox(10, newacc, newacc1, ctf1, newacc2, ctf2, newacc3, ctf3, newacc4, ctf4, newacc5,
				ctf5);
		vcreateLayout.getChildren().add(hcreateLayout);
		Scene createScene = new Scene(vcreateLayout, 650, 450);
		vcreateLayout.setAlignment(Pos.CENTER);
		hcreateLayout.setAlignment(Pos.CENTER);
		Stage CreateWindow = new Stage();
		CreateWindow.setTitle("Create New Account");
		CreateWindow.setScene(createScene);
		stage.centerOnScreen();

//Customer Window
//////////////////////////////////////////////////////////////////////////////////////////////	
		Label Title = new Label("Return to_Sleep Pizza");
		Title.setFont(new Font("Times New Roman", 32));
		Label toppings = new Label("Select the Toppings You Would Like");
		Button cLogoutBt = new Button("Logout, Customer"), goToCartBt = new Button("Go To Cart"); 
		Button addToCartBt = new Button("Add To Cart");Button removeFromCartBt = new Button("Undo Last Pizza");
		Button cancelOrderBt = new Button("Cancel Previous Order");
		CheckBox topping1 = new CheckBox("Cheese");
		topping1.setPadding(new Insets(30, 20, 10, 20));
		CheckBox topping2 = new CheckBox("Pepperoni");
		topping2.setPadding(new Insets(10, 20, 10, 20));
		CheckBox topping3 = new CheckBox("Green Peppers");
		topping3.setPadding(new Insets(10, 20, 10, 20));
		CheckBox topping4 = new CheckBox("Onions");
		topping4.setPadding(new Insets(10, 20, 10, 20));
		CheckBox topping5 = new CheckBox("Olives");
		topping5.setPadding(new Insets(10, 20, 60, 20));
		CheckBox topping6 = new CheckBox("Sausage");
		topping6.setPadding(new Insets(30, 20, 10, 20));
		CheckBox topping7 = new CheckBox("Ham");
		topping7.setPadding(new Insets(10, 20, 10, 20));
		CheckBox topping8 = new CheckBox("Pineapple");
		topping8.setPadding(new Insets(10, 20, 10, 20));
		CheckBox topping9 = new CheckBox("Anchovies");
		topping9.setPadding(new Insets(10, 20, 10, 20));
		CheckBox topping10 = new CheckBox("Chicken");
		topping10.setPadding(new Insets(10, 20, 60, 20));
		toppingList.add(topping1);
		toppingList.add(topping2);
		toppingList.add(topping3);
		toppingList.add(topping4);
		toppingList.add(topping5);
		toppingList.add(topping6);
		toppingList.add(topping7);
		toppingList.add(topping8);
		toppingList.add(topping9);
		toppingList.add(topping10);
		for(int i = 0; i < toppingList.size(); i++) {
			toppingList.get(i).setSelected(false);
		}
		toppingList.get(0).setSelected(true);
		HBox carthbox = new HBox(10, addToCartBt, removeFromCartBt, cancelOrderBt, goToCartBt, cLogoutBt);

		HBox hbox2 = new HBox();
		VBox primaryLayout = new VBox();
		VBox secondaryLayout = new VBox(topping1, topping2, topping3, topping4, topping5);
		VBox tirtiaryLayout = new VBox(topping6, topping7, topping8, topping9, topping10);
		hbox2.getChildren().addAll(secondaryLayout, tirtiaryLayout);
		primaryLayout.getChildren().addAll(Title, toppings, hbox2, carthbox);
		Scene secondScene = new Scene(primaryLayout, 800, 450);
		primaryLayout.setAlignment(Pos.CENTER);
		secondaryLayout.setAlignment(Pos.CENTER);
		carthbox.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);
		tirtiaryLayout.setAlignment(Pos.CENTER);
		Stage CustomerWindow = new Stage();
		CustomerWindow.setTitle("Customer");
		CustomerWindow.setScene(secondScene);
		stage.centerOnScreen();
		Stage newEmpWindow = new Stage();

//Employee window
////////////////////////////////////////////////////////////////////////////////////////////////		
		Button elogout = new Button(), punchIn = new Button(), punchOut = new Button(), ecreate = new Button();
		Label punchLabel = new Label();
		Label motivation1 = new Label("Get To Work");
		Label motivation2 = new Label ("Those Pizza's Aren't Going to Make Themselves");
		motivation1.setAlignment(Pos.CENTER);
		motivation2.setAlignment(Pos.CENTER);
		punchLabel.setText("");
		elogout.setText("Logout, Employee");
		punchIn.setText("Punch \nIn");
		punchOut.setText("Punch \nOut");
		ecreate.setText("Create new Employee");
		Label date = new Label();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		date.setText(dtf.format(now));
		HBox punchin_punchout = new HBox(20, punchIn, punchOut, ecreate, showPunches);
		VBox Layout = new VBox(20);
		Layout.getChildren().addAll(motivation1, motivation2, punchLabel, punchin_punchout, elogout, date);
		Layout.setAlignment(Pos.CENTER);
		punchin_punchout.setAlignment(Pos.CENTER);
		Scene empScene = new Scene(Layout, 650, 450);
		Stage EmployeeWindow = new Stage();
		EmployeeWindow.setTitle("Employee");
		EmployeeWindow.setScene(empScene);
		stage.centerOnScreen();

//Create Window handler
/////////////////////////////////////////////////////////////////////////////////////	
		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
				CreateWindow.show();

				// Return to login
				////////////////////////////////////////////////////////////////////
				logreturn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						label.setText("Welcome!");
						CAccess = false;
						EAccess = false;
						CreateWindow.close();
						stage.show();
					}
				});

//Logs in as new Customer account
//////////////////////////////////////////////////////////////////////////////// 
				newlog.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						
						if(ctf1.getText().isEmpty() != true && ctf2.getText().isEmpty() != true && ctf3.getText().isEmpty() != true && ctf4.getText().isEmpty() != true && ctf5.getText().isEmpty() != true) {
							Customer newCustomer = new Customer(Main.getNextID(), ctf3.getText(), ctf1.getText(), ctf2.getText(), ctf4.getText(), ctf5.getText());
							ctf1.setText("");ctf2.setText("");ctf3.setText("");ctf4.setText("");ctf5.setText("");
							File file = new File("Program_Files\\PersonData.txt");
							Main.addObjectToFile(newCustomer,file);
							Main.loadPersonDatabase();
							cust = newCustomer;
							CreateWindow.close();
							CAccess = true;
						}else {
							newacc.setText("No fields can be left blank");
						}
						if (CAccess) {
							stage.close();
							CustomerWindow.show();

							cLogoutBt.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									label.setText("Welcome!");
									tf1.setText("");
									tf2.setText("");
									CAccess = false;
									EAccess = false;
									CustomerWindow.close();
									stage.show();
								}
							});
						}
					}
				});
			}
		});
//Cart Window/Handler
/////////////////////////////////////////////////////////////////////////////////////////////////
		
//Removing an order from the cart
////////////////////////////////////////////////////////////////////////////////////////
		removeFromCartBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Label undoLabel;
				Group group = new Group();
				
				//If cart is empty
				if(pizzaCart.size() == 0) {
					undoLabel = new Label("Cart is empty");
					Line X = new Line();
					X.setStartX(25);
					X.setStartY(25);
					X.setEndX(75);
					X.setEndY(75);
					X.setStroke(Color.RED);
					X.setStrokeWidth(10);
					Line X2 = new Line();
					X2.setStartX(25);
					X2.setStartY(75);
					X2.setEndX(75);
					X2.setEndY(25);
					X2.setStroke(Color.RED);
					X2.setStrokeWidth(10);
					group.getChildren().addAll(X, X2);
				//Otherwise
				}else {
					pizzaCart.remove(pizzaCart.size()-1);
					undoLabel = new Label("Removed Last Pizza From Cart");
					pizzaCounter--;
					Line minus = new Line();
					minus.setStartX(25);
					minus.setStartY(75);
					minus.setEndX(125);
					minus.setEndY(75);
					minus.setStroke(Color.RED);
					minus.setStrokeWidth(10);
					group.getChildren().add(minus);
				}
		
				//Popup Screen Configs
				undoLabel.setPadding(new Insets(20, 20, 20, 20));
				Button closeButton = new Button("Close");
				closeButton.setPadding(new Insets(20, 20, 20, 20));
				undoLabel.setAlignment(Pos.CENTER);
				
				VBox addedVBox = new VBox(group, undoLabel, closeButton);
				addedVBox.setAlignment(Pos.CENTER);
				
				//Scene Preparation
				Scene added = new Scene(addedVBox , 200, 200);
				Stage addedWindow = new Stage();
				addedWindow.setScene(added);
				addedWindow.show();
				closeButton.setOnAction(e -> addedWindow.close());
			}
		});
		
//Canceling a previous order
////////////////////////////////////////////////////////////////////////////////////////
		cancelOrderBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				//A label of the orders that a Customer has placed
				Label currentOrders = new Label(Orders.printCurrentUserOrders(cust.getCustomerID()));
				currentOrders.setAlignment(Pos.CENTER);
				
				//If there are no previous orders
				if(currentOrders.getText().isEmpty()) {
					cancelHeaderLabel.setText("No orders to be removed");
				//Otherwise
				}else {
					cancelHeaderLabel.setText("Enter an order to remove:");
				}
				
				//Configs for the cancelVBox 
				TextField orderTF = new TextField();
				orderTF.setAlignment(Pos.CENTER);
				Button cancelOrderButton = new Button("Canel Order");
				Button closeButton = new Button("Close");
				cancelOrderButton.setPadding(new Insets(10, 10, 10, 10));
				closeButton.setPadding(new Insets(10, 20, 10, 20));
				
				Label cancelLabel = new Label();
				cancelLabel.setPadding(new Insets(20, 20, 20, 20));
				cancelLabel.setAlignment(Pos.CENTER);
				closeButton.setAlignment(Pos.CENTER);
				
				VBox cancelVBox = new VBox();
				
				cancelVBox = new VBox(cancelHeaderLabel, currentOrders, orderTF, cancelLabel, cancelOrderButton, closeButton);
				cancelVBox.setAlignment(Pos.CENTER);
				ScrollPane cancelScrollPane = new ScrollPane(cancelVBox);
				Scene cancel = new Scene(cancelScrollPane, 300, 300 );
				Stage addedWindow = new Stage();
				addedWindow.setScene(cancel);
				addedWindow.show();
				
				//Closes the current stage and returns to the main menu
				closeButton.setOnAction(e -> addedWindow.close());
				//Removes the order corresponding with the number entered into orderTF
				cancelOrderButton.setOnAction(e -> {
					if(orderTF.getText() != null && orderTF.getText().isEmpty() != true) {
						try{
							Integer enteredOrderNum = Integer.parseInt(orderTF.getText().toString());
							//Searching and removing the order based on the enteredOrderNum and CustomerID Pair. 
							Orders.removeOrder(new Pair<Integer,String>(enteredOrderNum,cust.getCustomerID()));
							currentOrders.setText(Orders.printCurrentUserOrders(cust.getCustomerID()));
						}catch(NumberFormatException nfe) {
							cancelHeaderLabel.setText("Input must be a number");
						}
					}else {
						cancelHeaderLabel.setText("Order number cannot be blank");
					}
				});
			}
		});
		
//Adding a pizza to the cart
////////////////////////////////////////////////////////////////////////////////////////
		addToCartBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				//List used to contain the toppings that were selected by the user
				ArrayList<String> tempToppingsList = new ArrayList<String>();
				
				//Collecting all selected toppings for the Pizza
				for(CheckBox cb: toppingList) {
					if(cb.isSelected())
						tempToppingsList.add(cb.getText().toString());
					cb.setSelected(false);
				}
				toppingList.get(0).setSelected(true);
				
				Button closeButton = new Button("Close");
				closeButton.setPadding(new Insets(20, 20, 20, 20));
				
				Label addedLabel = new Label();
				addedLabel.setPadding(new Insets(20, 20, 20, 20));
				addedLabel.setAlignment(Pos.CENTER);
				
				VBox addedVBox = new VBox();
				
				//If the user selected atleast one topping, create the pizza and add it to the pizzaCart
				if(tempToppingsList.size() != 0) {
					pizzaCounter++;
					pizzaCart.add(new Pizza(pizzaCounter,tempToppingsList));

					//Check Symbol
					Line checkPart1 = new Line();
					checkPart1.setStartX(50);
					checkPart1.setStartY(75);
					checkPart1.setEndX(75);
					checkPart1.setEndY(100);
					checkPart1.setStroke(Color.GREEN);
					checkPart1.setStrokeWidth(5);
					Line checkPart2 = new Line();
					checkPart2.setStartX(75);
					checkPart2.setStartY(100);
					checkPart2.setEndX(125);
					checkPart2.setEndY(40);
					checkPart2.setStroke(Color.GREEN);
					checkPart2.setStrokeWidth(5);
					addedLabel = new Label("Pizza Added to the Cart!");
					Group group = new Group(checkPart1, checkPart2);
					addedVBox = new VBox(group, addedLabel, closeButton);
				//Otherwise prompt user to select a topping
				}else {
					addedLabel = new Label("Please select at least one topping");
					addedVBox = new VBox(addedLabel, closeButton);
				}
				
				addedVBox.setAlignment(Pos.CENTER);
				
				//Setting Scene/Stage
				Scene added = new Scene(addedVBox , 200, 200);
				Stage addedWindow = new Stage();
				addedWindow.setScene(added);
				addedWindow.show();
				closeButton.setOnAction(e -> addedWindow.close());
			}
		});
		
//Displays cart contents
////////////////////////////////////////////////////////////////////////////////////////////////
		goToCartBt.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String pizzasInCart = "";
				HBox cartHb;
				VBox vbox1 = new VBox();
				
				//If Cart isn't empty, display Pizzas in pizzaCart
				if(pizzaCart.size() != 0) {
					cartHb = new HBox(new Label("You've Selected the following pizzas: \n\n"));
					vbox1.getChildren().add(cartHb);
					
					for(Pizza pizza : pizzaCart) {
						pizzasInCart += pizza.toString() + "\n";
					}
					vbox1.getChildren().add(new Label(pizzasInCart));
				//If Cart is empty
				}else {
					cartHb = new HBox(new Label("Cart is empty \n\n"));
					vbox1.getChildren().add(cartHb);
				}

				
				//Confirm Button
				Button confirmButton = new Button("Confirm Purchase");
				Button cancelButton = new Button("Cancel");
				cancelButton.setCancelButton(true);
				vbox1.getChildren().addAll(confirmButton, cancelButton);
				cartHb.setAlignment(Pos.CENTER);
				vbox1.setAlignment(Pos.CENTER);
				Scene cartScene = new Scene(vbox1, 500, 500);
				
				//Setting Stage
				Stage cartWindow = new Stage();
				cartWindow.setTitle("Customer");
				cartWindow.setScene(cartScene);
				stage.centerOnScreen();
				cartWindow.show();
				cancelButton.setOnAction(e -> cartWindow.close());
				
//Displays Receipt after Purchase
////////////////////////////////////////////////////////////////////////////////////////////				
				confirmButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						String receipt = "";
		
						//If the Cart is not empty
						if(!pizzaCart.isEmpty()){
							
							if(cust != null) {
								//Initializing a Pair to be assigned as the key to this order entry. 
								Pair<Integer,String> customerPair = new Pair<Integer,String>(Orders.generateOrderNumber(), cust.getCustomerID());
								
								//Adding customerPair and pizzaCart to an order. 
								Orders.addCompleteOrder(customerPair, pizzaCart);
								
								try {
									//Printing the receipt of the associated order
									receipt = Orders.printOrderRecipt(customerPair, pizzaCart);
									//Creating a text file of the receipt
									Orders.printOrderReciptToFile(customerPair, pizzaCart);
								} catch (IOException e) {
									e.printStackTrace();
								}finally{
									//Reseting Pizza variables
									pizzaCart = new ArrayList<Pizza>();
									pizzaCounter = 0;
								}
							}
							
							//Configs for receipt window
							cartWindow.close();
							Label thankYou = new Label("Thank You For Your Purchase!");
							Label receiptLabel = new Label(receipt);
							thankYou.setAlignment(Pos.CENTER);
							Font font = new Font(50);
							thankYou.setFont(font);
							VBox recieptVBox = new VBox(thankYou, receiptLabel);
							ScrollPane receiptPane = new ScrollPane(recieptVBox);
							
							//Setting Scene/Stage
							Scene purchaseScene = new Scene(receiptPane, 700, 500);
							Stage purchaseWindow = new Stage();
							purchaseWindow.setTitle("Customer");
							purchaseWindow.setScene(purchaseScene);
							stage.centerOnScreen();
							purchaseWindow.show();	
						
						}
						
						//Reseting Pizza variables
						pizzaCart = new ArrayList<Pizza>();
						pizzaCounter = 0;
					}
				});
			}
		});
		
//Closes Default window and terminates program
//////////////////////////////////////////////////////////////////////////////////
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
				System.exit(0);
			}
		});
		
		
//Handler for login button
//////////////////////////////////////////////////////////////////////////////////////////
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				Main.loadPersonDatabase();
				String userID = "";
				if (tf1.getText() != null && !tf1.getText().isEmpty() && 
						tf2.getText() != null && !tf2.getText().isEmpty()) {
					userID = Main.checkLoginCredentials(tf1.getText(), tf2.getText());
					if(userID != null) {
						if (userID.charAt(0) == 'C' || userID.charAt(0) == 'E') {
							if(userID.charAt(0) == 'C') {
								CAccess = true;
								cust = (Customer) Main.personDatabase.get(userID);
								//System.out.println("cust assigned");
							}else if (userID.charAt(0) == 'E') {
								EAccess = true;
								emp = (Employee) Main.personDatabase.get(userID);
								//System.out.println("emp assigned");
							}
						}
					
					}else {
						label.setText("Wrong Username and or Password");
						CAccess = false;
						EAccess = false;
					}
				}else {
					label.setText("Empty TextField");
				}
				
//Opens Customers
//////////////////////////////////////////////////////////////////////////////////////////////////
				if (CAccess) {
					stage.close();
					CustomerWindow.show();
					
					cLogoutBt.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							for(int i = 0; i < toppingList.size(); i++) {
								toppingList.get(i).setSelected(false);
							}
							pizzaCart = new ArrayList<Pizza>();
							toppingList.get(0).setSelected(true);
							label.setText("Welcome!");
							tf1.setText("");
							tf2.setText("");
							CAccess = false;
							EAccess = false;
							CustomerWindow.close();
							stage.show();
						}
					});
				}

//Employee Screen
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if (EAccess) {
					
					stage.close();
					EmployeeWindow.show();
					motivation1.setText("Get To Work");
					motivation2.setText("Those Pizza's Aren't Going to Make Themselves");
					punchLabel.setText("");
					punchIn.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							SimpleDateFormat time_format = new SimpleDateFormat("hh:mm:ss");
							Date date = new Date();
							String current_time = time_format.format(date);
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
							LocalDateTime DateAndTime = LocalDateTime.now();
							punchLabel.setText("Punched In At: " + current_time);
							punchInTime = current_time;
							File punchFile = new File("Program_Files\\PunchData.txt");
							Main.addPunchToFile(emp.getEmployeeID() + ";Punched In;" +  dtf.format(DateAndTime), punchFile);
						}
					});
					punchOut.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							SimpleDateFormat time_format = new SimpleDateFormat("hh:mm:ss");
							Date date = new Date();
							String current_time = time_format.format(date);
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
							LocalDateTime DateAndTime = LocalDateTime.now();
							punchLabel.setText("Punched In Out: " + current_time);
							punchOutTime = current_time;
							File punchFile = new File("Program_Files\\PunchData.txt");
							Main.addPunchToFile(emp.getEmployeeID() + ";Punched Out;" +  dtf.format(DateAndTime), punchFile);
						}
					});
					showPunches.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							Main.loadPunchDatabase();
							Label punchLabel = new Label("Employee Punches");
							punchLabel.setAlignment(Pos.CENTER);
							Font font = new Font(30);
							Label punches = new Label();
							//System.out.println(Main.punchDatabase);
							//System.out.println(Main.punchDatabase.get(0).get(0));
							for(List<String> l: Main.punchDatabase) {
								for(String s: l) {
									//temp += s;
									//System.out.println(s);
									punches.setText(punches.getText() + s + " ");
								}
								punches.setText(punches.getText() + "\n");
							}
							punchLabel.setFont(font);
							VBox PunchVBox = new VBox(punchLabel, punches);
							ScrollPane punchPane = new ScrollPane(PunchVBox);
							Scene punchScene = new Scene(punchPane, 400, 300);
							Stage punchStage = new Stage();
							punchStage.setTitle("Employee");
							punchStage.setScene(punchScene);
							stage.centerOnScreen();
							punchStage.show();
						}
					});
					
//Employee account creation
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
					ecreate.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							
							// no longer closes previous window
							//EmployeeWindow.close();
							// Creates all the fields and buttons need for making a new account
							if(emp.getPosition().equals("Manager")) {
								etf1.setText("");etf2.setText("");etf3.setText("");etf4.setText("");etf5.setText("");
								etf6.setText("");etf7.setText("");
								 //position, double wage, double yearToDateHours, boolean isFullTime
								
								Label newacc1 = new Label("Username:"), newacc2 = new Label("Password:"),
										newacc3 = new Label("Name:"), newacc4 = new Label("Address:"),
										newacc5 = new Label("Phone Number:"), newacc6 = new Label("position:"),
										newacc7 = new Label("wage:"), newacc8 = new Label("isFullTime:");
								etf1.setMaxWidth(240);
								etf2.setMaxWidth(240);
								etf3.setMaxWidth(240);
								etf4.setMaxWidth(240);
								etf5.setMaxWidth(240);
								etf6.setMaxWidth(240);
								etf7.setMaxWidth(240);
								// Creates Hboxes and Vboxes for format the GUI
								HBox hcreateLayout = new HBox(40, goBack, newEmp);
								VBox vcreateLayout = new VBox(10, newacc1, etf1, newacc2, etf2, newacc3, etf3,
										newacc4, etf4);
								VBox vcreateSecondLayout = new VBox(10, newacc5, etf5, newacc6, etf6, newacc7, etf7, newacc8, cb);
								vcreateSecondLayout.setAlignment(Pos.CENTER);
								vcreateLayout.getChildren().add(hcreateLayout);
								vcreateSecondLayout.setPadding(new Insets(20, 20, 20, 20));
								vcreateLayout.setPadding(new Insets(20, 20, 20, 20));
								HBox fields = new HBox(vcreateLayout, vcreateSecondLayout);
								fields.setAlignment(Pos.CENTER);
								fields.setPadding(new Insets(20, 20, 20, 20));
								VBox outerVbox = new VBox( newEmployeeLabel, fields, hcreateLayout);
								outerVbox.setAlignment(Pos.CENTER);
								Scene createScene = new Scene(outerVbox, 650, 450);
								vcreateLayout.setAlignment(Pos.CENTER);
								hcreateLayout.setAlignment(Pos.CENTER);
								// Creates the window for making a new account
								newEmpWindow.setTitle("Create New Employee Account");
								newEmpWindow.setScene(createScene);
								stage.centerOnScreen();
								newEmpWindow.show();
							}else {
								motivation1.setText("No");
								motivation2.setText("Managers Only");
							}
						
							// Returns the user from the create new account window to the login window

							
						}
					});
					goBack.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							System.out.println("goBack ran");
							label.setText("Welcome!");
							tf1.setText("");
							tf2.setText("");
							CAccess = false;
							EAccess = false;
							newEmpWindow.close();
						}
					});

					newEmp.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							
							try{
								if(etf1.getText().isEmpty() == false && etf2.getText().isEmpty() == false &&
									etf3.getText().isEmpty() == false &&etf4.getText().isEmpty() == false &&
									etf5.getText().isEmpty() == false &&etf6.getText().isEmpty() == false &&
									etf7.getText().isEmpty() == false) {
								Employee newEmployee = new Employee(Main.getNextID(),etf3.getText(),etf1.getText(), 
									etf2.getText(),etf4.getText(),etf5.getText(),etf6.getText(),
								Double.parseDouble(etf7.getText()), cb.isSelected());
								File file = new File("Program_Files\\PersonData.txt");
								Main.addObjectToFile(newEmployee, file);
								newEmpWindow.close();
							}else {
								newEmployeeLabel.setText("Must enter data in all fields");
							}
							}catch(NumberFormatException nfe) {
								newEmployeeLabel.setText("Input for wage must be a number");
							}
						}
					});
					
////////////////////////////////////////////////////////////////////////////////////////////////////////////					
//This event handles the use logging out and returns to the main screen
					elogout.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							label.setText("Welcome!");
							tf1.setText("");
							tf2.setText("");
							CAccess = false;
							EAccess = false;
							EmployeeWindow.close();
							stage.show();
						}
					});
				}
			}
		});
	}
	public static void main(String[] args) {
		Main.main(null);
		launch(args);
	}
}
