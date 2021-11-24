import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class GUI extends Application {
	String Customer = "C";
	String Employee = "E";
	boolean CAccess, EAccess = false;
	String CPass = "c";
	String EPass = "e";
	String[] menuitems = { "Pizza1", "Pizza2", "Pizza3"};
	@Override
	public void start(final Stage stage) {
		Button button = new Button("Login");
		Button quit = new Button("Exit");
		Button create = new Button("Create new account");
		Label label = new Label("Welcome!");
		Label label1 = new Label("Username:");
		Label label2 = new Label("Password:");
//		label.setTextFill(Color.web("#ff0000"));
		TextField tf1 = new TextField(), tf2 = new TextField();
		HBox hbox = new HBox(20, quit, create, button);
		VBox pane = new VBox(10, label1, tf1, label2, tf2, label);
		pane.getChildren().add(hbox);
		tf1.setMaxWidth(240);
		tf2.setMaxWidth(240);
		pane.setAlignment(Pos.CENTER);
		hbox.setAlignment(Pos.CENTER);
//handler for creating a new account which opens a new window
		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//Closes previous window
				stage.close();
//Creates all the fields and buttons need for making a new account
				TextField ctf1 = new TextField(), ctf2 = new TextField(), ctf3 = new TextField(),
						ctf4 = new TextField();
				Button logreturn = new Button("Return to login");
				Button newlog = new Button("Create and login");
				Label newacc = new Label("Please create a new username and password");
				Label newacc1 = new Label("Username:"), newacc2 = new Label("Password:"), newacc3 = new Label("Name:"),
						newacc4 = new Label("Address:");
				ctf1.setMaxWidth(240);
				ctf2.setMaxWidth(240);
				ctf3.setMaxWidth(240);
				ctf4.setMaxWidth(240);
//Creates Hboxes and Vboxes for format the GUI
				HBox hcreateLayout = new HBox(40, logreturn, newlog);
				VBox vcreateLayout = new VBox(10, newacc, newacc1, ctf1, newacc2, ctf2, newacc3, ctf3, newacc4, ctf4);
				vcreateLayout.getChildren().add(hcreateLayout);
				Scene createScene = new Scene(vcreateLayout, 650, 450);
				vcreateLayout.setAlignment(Pos.CENTER);
				hcreateLayout.setAlignment(Pos.CENTER);
//Creates the window for making a new account
				Stage CreateWindow = new Stage();
				CreateWindow.setTitle("Create New Account");
				CreateWindow.setScene(createScene);
				stage.centerOnScreen();
				CreateWindow.show();
//Returns the user from the create new account window to the login window
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
//////Handler for logging in as the newly created account needs to write to file if account does not already exist/////
				newlog.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						CreateWindow.close();
///////This if statement needs to check if the account already exists////////////////
						CAccess = true;
//This opens the customer screen after the customer creates an account
						if (CAccess) {
							stage.close();
////////////For statement makes number of labels depending on pizza's available///////////
							Label Labels[];
							Labels = new Label[menuitems.length];
							for (int i = 0; i < Labels.length; i++) {
								Labels[i] = new Label(menuitems[i]);
							}
							Label Title = new Label("Name of pizza place");
							Button secondbutton = new Button("Button"), cart = new Button("Cart");
							secondbutton.setText("Logout, Customer");
//////////////////Creates a number of buttons depending on the number of pizza's available and sets their handlers to a method 
//which will add the pizza's to user cart///////////////////////////////////////////
							Button addToCarts[];
							addToCarts = new Button[menuitems.length];
							for (int i = 0; i < addToCarts.length; i++) {
								addToCarts[i] = new Button("Add item " + (i + 1) + " to cart");

								addToCarts[i].setOnAction((ActionEvent e) -> {
									Button b = (Button) e.getSource();
									String pizza = b.getText();
									getPizza(pizza);
								});
							}
///////////////Creates a number of Hboxes depending on how many pizza's are available/////////////////////
							HBox hbox = new HBox(10, Title, cart, secondbutton);
							HBox hbox1[] = null;
							hbox1 = new HBox[menuitems.length];
							VBox secondaryLayout = new VBox();
							for (int i = 0; i < hbox1.length; i++) {
								hbox1[i] = new HBox(10, Labels[i], addToCarts[i]);
							}
//Places all the Hboxes into the Vbox to format the GUI
							secondaryLayout.getChildren().add(hbox);
							for (int i = 0; i < hbox1.length; i++) {
								secondaryLayout.getChildren().add(hbox1[i]);
							}
//Creates the scene for the stage
							Scene secondScene = new Scene(secondaryLayout, 650, 450);
							secondaryLayout.setAlignment(Pos.CENTER);
							hbox.setAlignment(Pos.CENTER);
//Centers all of the HBoxes
							for (int i = 0; i < hbox1.length; i++) {
								hbox1[i].setAlignment(Pos.CENTER);
							}
//Creates the Customer window
							Stage CustomerWindow = new Stage();
							CustomerWindow.setTitle("Customer");
							CustomerWindow.setScene(secondScene);
							stage.centerOnScreen();
							CustomerWindow.show();
/////////////This will open the user cart and allow them to check out/////////////////////
							cart.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									System.out.print("Show Cart");
								}
							});
//Logs out the customer
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
//Handler for quit button
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
		});
//Handler for login button
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//////////////if user and pass exist then grants access to either employee screen or customer screen/////////////////
				if (Customer.equals(tf1.getText()) && CPass.equals(tf2.getText()))
					CAccess = true;
				else if (Employee.equals(tf1.getText()) && EPass.equals(tf2.getText()))
					EAccess = true;
				else
					label.setText("Username or Password was incorrect");
//opens Customer Screen	if user login was a customer	
				if (CAccess) {
					stage.close();
//For statement makes number of labels depending on pizza's available
					Label Labels[];
					Labels = new Label[menuitems.length];
					for (int i = 0; i < Labels.length; i++) {
						Labels[i] = new Label(menuitems[i]);
					}
					Label Title = new Label("Name of pizza place");
					Button secondbutton = new Button("Button"), cart = new Button("Cart");
					secondbutton.setText("Logout, Customer");
//////////////////Creates a number of buttons depending on the number of pizza's available and sets their handlers to a method 
//which will add the pizza's to user cart///////////////////////////////////////////
					Button addToCarts[];
					addToCarts = new Button[menuitems.length];
					for (int i = 0; i < addToCarts.length; i++) {
						addToCarts[i] = new Button("Add item " + (i + 1) + " to cart");

						addToCarts[i].setOnAction((ActionEvent e) -> {
							Button b = (Button) e.getSource();
							String pizza = b.getText();
							getPizza(pizza);
						});
					}
					HBox hbox = new HBox(10, Title, cart, secondbutton);
///////////////Creates a number of Hboxes depending on how many pizza's are available/////////////////////
					HBox hbox1[] = null;
					hbox1 = new HBox[menuitems.length];
					VBox secondaryLayout = new VBox();
					for (int i = 0; i < hbox1.length; i++) {
						hbox1[i] = new HBox(10, Labels[i], addToCarts[i]);
					}
//Places all the Hboxes into the Vbox to format the GUI
					secondaryLayout.getChildren().add(hbox);
					for (int i = 0; i < hbox1.length; i++) {
						secondaryLayout.getChildren().add(hbox1[i]);
					}
//Creates the scene for the stage
					Scene secondScene = new Scene(secondaryLayout, 650, 450);
					secondaryLayout.setAlignment(Pos.CENTER);
					hbox.setAlignment(Pos.CENTER);
//Centers all of the HBoxes
					for (int i = 0; i < hbox1.length; i++) {
						hbox1[i].setAlignment(Pos.CENTER);
					}
//Creates the Customer window
					Stage CustomerWindow = new Stage();
					CustomerWindow.setTitle("Customer");
					CustomerWindow.setScene(secondScene);
					stage.centerOnScreen();
					CustomerWindow.show();
/////////////This will open the user cart and allow them to check out/////////////////////
					cart.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							System.out.print("Show Cart");
						}
					});
//Returns the customer back to the login window
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
				if (EAccess) {
//closes previous window
					stage.close();
//Creates buttons and labels
					Button elogout = new Button(), punchIn = new Button(), punchOut = new Button();
					elogout.setText("Logout, Employee");
					punchIn.setText("Punch \nIn");
					punchOut.setText("Punch \nOut");
					Label date = new Label();
//formats the date and time and puts it into a label to display
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					date.setText(dtf.format(now));
//Creates H and V boxes to make the GUI
					HBox punchin_punchout = new HBox(20, punchIn, punchOut);
					VBox secondaryLayout = new VBox(20);
					secondaryLayout.getChildren().addAll(punchin_punchout, elogout, date);
					secondaryLayout.setAlignment(Pos.CENTER);
					punchin_punchout.setAlignment(Pos.CENTER);
//Creates the new window for the employee side of the program
					Scene secondScene = new Scene(secondaryLayout, 650, 450);
					Stage EmployeeWindow = new Stage();
					EmployeeWindow.setTitle("Employee");
					EmployeeWindow.setScene(secondScene);
					stage.centerOnScreen();
					EmployeeWindow.show();
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
//This creates the initial window for user login
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Pizza Project");
		stage.setWidth(300);
		stage.show();
	}
//Depending on the button pressed this needs to add that pizza to the cart
	private void getPizza(String menuitem) {
		if (menuitems[0] != null) {
			switch (menuitem) {
			case "Add item 1 to cart":
				System.out.print("Pizza1");
			}
		}
		if (menuitems[1] != null) {
			switch (menuitem) {
			case "Add item 2 to cart":
				System.out.print("Pizza2");
			}
		}
		if (menuitems[2] != null) {
			switch (menuitem) {
			case "Add item 3 to cart":
				System.out.print("Pizza3");
			}
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}