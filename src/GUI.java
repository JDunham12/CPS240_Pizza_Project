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

//Punch in, Punch out, Checkboxes, stitching
public class GUI extends Application {
//place holders
	String Customer = "C";
	String Employee = "E";
	String CPass = "c";
	String EPass = "e";
	String[] menuitems = {"Cheese","Pepperoni","Green Peppers","Onions","Olives","Sausage","Ham","Pineapple","Anchovies","Chicken"};
	Boolean[] toppingsSelected = {false, false, false, false, false, false, false, false, false, false};
	List<CheckBox> toppingList = new ArrayList<CheckBox>();
	String punchOutTime = "";
	String punchInTime = "";
	boolean CAccess, EAccess = false;

	@Override
	public void start(final Stage stage) {

//Sets up the first window to be launched
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
						ctf4 = new TextField(), ctf5 = new TextField();
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
//Creates Hboxes and Vboxes for format the GUI
				HBox hcreateLayout = new HBox(40, logreturn, newlog);
				VBox vcreateLayout = new VBox(10, newacc, newacc1, ctf1, newacc2, ctf2, newacc3, ctf3, newacc4, ctf4,
						newacc5, ctf5);
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
							Label Title = new Label("Name of pizza place");
							Button secondbutton = new Button("Button"), cart = new Button("Cart");
							secondbutton.setText("Logout, Customer");
///////////////Creates a number of Hboxes depending on how many pizza's are available/////////////////////
							HBox hbox = new HBox(10, Title, cart, secondbutton);
							VBox secondaryLayout = new VBox();

//Places all the Hboxes into the Vbox to format the GUI
							secondaryLayout.getChildren().add(hbox);
//Creates the scene for the stage
							Scene secondScene = new Scene(secondaryLayout, 650, 450);
							secondaryLayout.setAlignment(Pos.CENTER);
							hbox.setAlignment(Pos.CENTER);
//Centers all of the HBoxes
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
					Label Title = new Label("Return to_Sleep Pizza");
					Label label = new Label("Select the Toppings You Would Like");
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
					
					HBox hbox = new HBox(10, Title, cart, secondbutton);
					HBox hbox2 = new HBox();
///////////////Creates a number of Hboxes depending on how many pizza's are available/////////////////////
					VBox primaryLayout = new VBox();
					VBox secondaryLayout = new VBox(topping1, topping2, topping3, topping4, topping5);
					
					VBox tirtiaryLayout = new VBox(topping6, topping7, topping8, topping9, topping10);
//Places all the Hboxes into the Vbox to format the GUI
					hbox2.getChildren().addAll(secondaryLayout, tirtiaryLayout);
					primaryLayout.getChildren().addAll(label, hbox2, hbox);
//Creates the scene for the stage
					Scene secondScene = new Scene(primaryLayout, 650, 450);
					primaryLayout.setAlignment(Pos.CENTER);
					secondaryLayout.setAlignment(Pos.CENTER);
					hbox.setAlignment(Pos.CENTER);
					hbox2.setAlignment(Pos.CENTER);
					tirtiaryLayout.setAlignment(Pos.CENTER);
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
							for(int i = 0; i < 10; i++) {
								toppingsSelected[i] = toppingList.get(i).isSelected();
								System.out.println("#" + i + " " + toppingList.get(i).isSelected() + " ");
							}
							VBox vbox1 = new VBox();
							
							Label label = new Label("You Selected a Pizza With: \n\n");
							HBox cartHb = new HBox(label);
							vbox1.getChildren().add(cartHb);
							for(CheckBox b: toppingList) {
								if(b.isSelected() == true) {
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
							
							//Creates the Customer window
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
					Button elogout = new Button(), punchIn = new Button(), punchOut = new Button(); Label punchLabel = new Label();
					punchLabel.setText("");
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
					secondaryLayout.getChildren().addAll(punchLabel, punchin_punchout, elogout, date);
					secondaryLayout.setAlignment(Pos.CENTER);
					punchin_punchout.setAlignment(Pos.CENTER);
//Creates the new window for the employee side of the program
					Scene secondScene = new Scene(secondaryLayout, 650, 450);
					Stage EmployeeWindow = new Stage();
					EmployeeWindow.setTitle("Employee");
					EmployeeWindow.setScene(secondScene);
					stage.centerOnScreen();
					EmployeeWindow.show();
					
					punchIn.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
					        SimpleDateFormat time_format=new SimpleDateFormat("hh:mm:ss");
					        Date date=new Date();
					        String current_time=time_format.format(date);
					        punchLabel.setText("Punched In At: " + current_time);
					        punchInTime = current_time;
						}
					});
					punchOut.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							SimpleDateFormat time_format=new SimpleDateFormat("hh:mm:ss");
					        Date date=new Date();
					        String current_time=time_format.format(date);
					        punchLabel.setText("Punched In Out: " + current_time);
					        punchOutTime = current_time;
						}
					});
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					

					create.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							// Closes previous window

							stage.close();
							// Creates all the fields and buttons need for making a new account
							TextField ctf1 = new TextField(), ctf2 = new TextField(), ctf3 = new TextField(),
									ctf4 = new TextField(), ctf5 = new TextField();
							Button logreturn = new Button("Return to login");
							Button newlog = new Button("Create and login");
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
							CreateWindow.setTitle("Create New Account");
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

							////// Handler for logging in as the newly created account needs to write to
							////// file if account does not already exist/////

							newlog.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									CreateWindow.close();
									/////// This if statement needs to check if the account already
									/////// exists////////////////
									CAccess = true;
									// This opens the customer screen after the customer creates an account
									if (CAccess) {
										stage.close();
										//////////// For statement makes number of labels depending on pizza's
										//////////// available///////////
										Label Labels[];
										Labels = new Label[menuitems.length];
										for (int i = 0; i < Labels.length; i++) {
											Labels[i] = new Label(menuitems[i]);
										}
										Label Title = new Label("Return to_Sleep Pizza");
										Button secondbutton = new Button("Button"), cart = new Button("Cart");
										secondbutton.setText("Logout, Customer");

										HBox hbox = new HBox(10, Title, cart, secondbutton);
										HBox hbox1[] = null;
										hbox1 = new HBox[menuitems.length];
										VBox secondaryLayout = new VBox();
										// Places all the Hboxes into the Vbox to format the GUI
										secondaryLayout.getChildren().add(hbox);

										// Creates the scene for the stage
										Scene secondScene = new Scene(secondaryLayout, 650, 450);
										secondaryLayout.setAlignment(Pos.CENTER);
										hbox.setAlignment(Pos.CENTER);
										// Centers all of the HBoxes

										// Creates the Customer window
										Stage CustomerWindow = new Stage();
										CustomerWindow.setTitle("Customer");
										CustomerWindow.setScene(secondScene);
										stage.centerOnScreen();
										CustomerWindow.show();
										///////////// This will open the user cart and allow them to check
										///////////// out/////////////////////

										cart.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent event) {
												System.out.print("Show Cart");
											}
										});

										// Logs out the customer

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

//This creates the initial window for user login
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Pizza Project");
		stage.setWidth(300);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}