import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GUI extends Application {
//place holders
	String Customer = "C";
	String Employee = "E";
	String CPass = "c";
	String EPass = "e";
	String[] menuitems = { "Cheese", "Pepperoni", "Green Peppers", "Onions", "Olives", "Sausage", "Ham", "Pineapple",
			"Anchovies", "Chicken" };
	Boolean[] toppingsSelected = { false, false, false, false, false, false, false, false, false, false };
	List<CheckBox> toppingList = new ArrayList<CheckBox>();
	String punchOutTime = "";
	String punchInTime = "";
	boolean CAccess, EAccess = false;

	@Override
	public void start(final Stage stage) {

//Sets up the first window to be launched
////////////////////////////////////////////////////////////////////////////////////////////////
		Button button = new Button("Login");
		Button quit = new Button("Exit");
		Button create = new Button("Create new account");
		Label label = new Label("Welcome!");
		Label label1 = new Label("Username:");
		Label label2 = new Label("Password:");
		TextField tf1 = new TextField(), tf2 = new TextField();
		HBox hbox = new HBox(20, quit, create, button);
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
		Button logreturn = new Button("Return to login");
		Button newlog = new Button("Create and login");
		Label newacc = new Label("Please create a new username and password");
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
		Label toppings = new Label("Select the Toppings You Would Like");
		Button secondbutton = new Button("Button"), cart = new Button("Cart");
		secondbutton.setText("Logout, Customer");
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
		HBox chbox = new HBox(10, Title, cart, secondbutton);
		HBox hbox2 = new HBox();
		VBox primaryLayout = new VBox();
		VBox secondaryLayout = new VBox(topping1, topping2, topping3, topping4, topping5);
		VBox tirtiaryLayout = new VBox(topping6, topping7, topping8, topping9, topping10);
		hbox2.getChildren().addAll(secondaryLayout, tirtiaryLayout);
		primaryLayout.getChildren().addAll(toppings, hbox2, chbox);
		Scene secondScene = new Scene(primaryLayout, 650, 450);
		primaryLayout.setAlignment(Pos.CENTER);
		secondaryLayout.setAlignment(Pos.CENTER);
		chbox.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);
		tirtiaryLayout.setAlignment(Pos.CENTER);
		Stage CustomerWindow = new Stage();
		CustomerWindow.setTitle("Customer");
		CustomerWindow.setScene(secondScene);
		stage.centerOnScreen();

//Employee window
////////////////////////////////////////////////////////////////////////////////////////////////		
		Button elogout = new Button(), punchIn = new Button(), punchOut = new Button(), ecreate = new Button();
		Label punchLabel = new Label();
		punchLabel.setText("");
		elogout.setText("Logout, Employee");
		punchIn.setText("Punch \nIn");
		punchOut.setText("Punch \nOut");
		ecreate.setText("Create new Employee");
		Label date = new Label();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		date.setText(dtf.format(now));
		HBox punchin_punchout = new HBox(20, punchIn, punchOut, ecreate);
		VBox Layout = new VBox(20);
		Layout.getChildren().addAll(punchLabel, punchin_punchout, elogout, date);
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
						tf1.setText("");
						tf2.setText("");
						CAccess = false;
						EAccess = false;
						CreateWindow.close();
						stage.show();
					}
				});

//Logs in as new account
////////////////////////////////////////////////////////////////////////////////
				newlog.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						CreateWindow.close();
						CAccess = true;
						if (CAccess) {
							stage.close();
							CustomerWindow.show();

							secondbutton.setOnAction(new EventHandler<ActionEvent>() {
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
		cart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < 10; i++) {
					toppingsSelected[i] = toppingList.get(i).isSelected();
					System.out.println("#" + i + " " + toppingList.get(i).isSelected() + " ");
				}
				VBox vbox1 = new VBox();

				Label label = new Label("You Selected a Pizza With: \n\n");
				HBox cartHb = new HBox(label);
				vbox1.getChildren().add(cartHb);
				for (CheckBox b : toppingList) {
					if (b.isSelected() == true) {
						Label toppingLabel = new Label();
						toppingLabel.setText(b.getText());
						vbox1.getChildren().add(toppingLabel);
					}
				}

				Button confirmButton = new Button("Confirm Purchase");
				Button cancelButton = new Button("Cancel");
				cancelButton.setCancelButton(true);
				vbox1.getChildren().addAll(confirmButton, cancelButton);
				cartHb.setAlignment(Pos.CENTER);
				vbox1.setAlignment(Pos.CENTER);
				Scene cartScene = new Scene(vbox1, 500, 500);

				Stage cartWindow = new Stage();
				cartWindow.setTitle("Customer");
				cartWindow.setScene(cartScene);
				stage.centerOnScreen();
				cartWindow.show();
				cancelButton.setOnAction(e -> cartWindow.close());
				
				confirmButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						cartWindow.close();
						Label thankYou = new Label("Thank You For Your Purchase!");
						thankYou.setAlignment(Pos.CENTER);
						Font font = new Font(50);
						thankYou.setFont(font);
						Scene purchaseScene = new Scene(thankYou, 700, 500);
						Stage purchaseWindow = new Stage();
						purchaseWindow.setTitle("Customer");
						purchaseWindow.setScene(purchaseScene);
						stage.centerOnScreen();
						purchaseWindow.show();
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
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (Customer.equals(tf1.getText()) && CPass.equals(tf2.getText()))
					CAccess = true;
				else if (Employee.equals(tf1.getText()) && EPass.equals(tf2.getText()))
					EAccess = true;
				else
					label.setText("Username or Password was incorrect");
//Opens Customers
//////////////////////////////////////////////////////////////////////////////////////////////////
				if (CAccess) {
					stage.close();
					CustomerWindow.show();
					
					secondbutton.setOnAction(new EventHandler<ActionEvent>() {
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

//Employee Screen
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if (EAccess) {
					stage.close();
					EmployeeWindow.show();
					punchIn.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							SimpleDateFormat time_format = new SimpleDateFormat("hh:mm:ss");
							Date date = new Date();
							String current_time = time_format.format(date);
							punchLabel.setText("Punched In At: " + current_time);
							punchInTime = current_time;
						}
					});
					punchOut.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							SimpleDateFormat time_format = new SimpleDateFormat("hh:mm:ss");
							Date date = new Date();
							String current_time = time_format.format(date);
							punchLabel.setText("Punched In Out: " + current_time);
							punchOutTime = current_time;
						}
					});
					
//Employee account creation
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
					ecreate.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							// Closes previous window

							EmployeeWindow.close();
							// Creates all the fields and buttons need for making a new account
							TextField ctf1 = new TextField(), ctf2 = new TextField(), ctf3 = new TextField(),
									ctf4 = new TextField(), ctf5 = new TextField();
							Button logreturn = new Button("Return to login");
							Button newlog = new Button("Create new employee");
							Label newacc = new Label("Please create a new username and password");
							Label newacc1 = new Label("Username:"), newacc2 = new Label("Password:"),
									newacc3 = new Label("Name:"), newacc4 = new Label("Address:"),
									newacc5 = new Label("Phone Number:");
							ctf1.setMaxWidth(240);
							ctf2.setMaxWidth(240);
							ctf3.setMaxWidth(240);
							ctf4.setMaxWidth(240);
							ctf5.setMaxWidth(240);
							// Creates Hboxes and Vboxes for format the GUI
							HBox hcreateLayout = new HBox(40, logreturn, newlog);
							VBox vcreateLayout = new VBox(10, newacc, newacc1, ctf1, newacc2, ctf2, newacc3, ctf3,
									newacc4, ctf4, newacc5, ctf5);
							vcreateLayout.getChildren().add(hcreateLayout);
							Scene createScene = new Scene(vcreateLayout, 650, 450);
							vcreateLayout.setAlignment(Pos.CENTER);
							hcreateLayout.setAlignment(Pos.CENTER);
							// Creates the window for making a new account
							Stage CreateWindow = new Stage();
							CreateWindow.setTitle("Create New Employee Account");
							CreateWindow.setScene(createScene);
							stage.centerOnScreen();
							CreateWindow.show();

							// Returns the user from the create new account window to the login window

							logreturn.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									label.setText("Welcome!");
									tf1.setText("");
									tf2.setText("");
									CAccess = false;
									EAccess = false;
									CreateWindow.close();
									stage.show();
								}
							});

							newlog.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {

								}
							});
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
		launch(args);
	}
}