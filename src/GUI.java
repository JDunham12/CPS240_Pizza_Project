import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import java.util.*;

public class GUI extends Application {
	String Customer = "C";
	String Employee = "E";
	boolean CAccess, EAccess = false;
	String CPass = "c";
	String EPass = "e";
	String[] menuitems = {"Pizza1", "Pizza2", "Pizza3"};

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

		HBox hbox = new HBox(20,quit,create,button);
		VBox pane = new VBox(10,label1, tf1, label2, tf2, label);
		pane.getChildren().add(hbox);
		
		tf1.setMaxWidth(240);
		tf2.setMaxWidth(240);
		pane.setAlignment(Pos.CENTER);
		hbox.setAlignment(Pos.CENTER);
		
		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
				TextField ctf1 = new TextField(), ctf2 = new TextField(), ctf3 = new TextField();
				Button logreturn = new Button("Return to login");
				Button newlog = new Button("Create and login");
				Label newacc = new Label("Please create a new username and password");
				Label newacc1 = new Label("Username:"), newacc2 = new Label("Password:"), newacc3 = new Label("Confirm Password:");
				ctf1.setMaxWidth(240);
				ctf2.setMaxWidth(240);
				ctf3.setMaxWidth(240);
				
				HBox hcreateLayout = new HBox(40, logreturn, newlog);
				VBox vcreateLayout = new VBox(10, newacc, newacc1, ctf1, newacc2, ctf2, newacc3, ctf3);
				vcreateLayout.getChildren().add(hcreateLayout);

				Scene createScene = new Scene(vcreateLayout, 650, 450);
				vcreateLayout.setAlignment(Pos.CENTER);
				hcreateLayout.setAlignment(Pos.CENTER);
				// new window
				Stage CreateWindow = new Stage();
				CreateWindow.setTitle("Create New Account");
				CreateWindow.setScene(createScene);

				stage.centerOnScreen();

				CreateWindow.show();
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
		
		
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
		});
		
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (Customer.equals(tf1.getText()) && CPass.equals(tf2.getText()))
					CAccess = true;
				else if(Employee.equals(tf1.getText()) && EPass.equals(tf2.getText()))
					EAccess = true;
				else
					label.setText("Username or Password was incorrect");
				
//Customer Screen				
				if (CAccess) {
					stage.close();
					
					Label Labels[];
					Labels = new Label[menuitems.length];
					for(int i = 0; i < Labels.length; i++) {
						Labels[i] = new Label(menuitems[i]);
					}

					Label Title = new Label("Name of pizza place");
					Button secondbutton = new Button("Button"), cart = new Button("Cart");
					secondbutton.setText("Logout, Customer");
					
					Button addToCarts[];
					addToCarts = new Button[menuitems.length];
					for(int i = 0; i < addToCarts.length; i++) {
						addToCarts[i] = new Button("Add to cart");
					}
					
					HBox hbox = new HBox(10, Title, cart ,secondbutton);
					
					
					HBox hbox1[] = null;
					hbox1 = new HBox[menuitems.length];
					VBox secondaryLayout = new VBox();
					for(int i = 0; i < hbox1.length; i++) {
						hbox1[i] = new HBox(10,Labels[i],addToCarts[i]);
					}
					
					secondaryLayout.getChildren().add(hbox);
					for(int i = 0; i < hbox1.length; i++) {
						secondaryLayout.getChildren().add(hbox1[i]);
					}
					
					Scene secondScene = new Scene(secondaryLayout, 650, 450);
					secondaryLayout.setAlignment(Pos.CENTER);
					hbox.setAlignment(Pos.CENTER);
					for(int i = 0; i < hbox1.length; i++) {
						hbox1[i].setAlignment(Pos.CENTER);
					}
					// new window
					Stage CustomerWindow = new Stage();
					CustomerWindow.setTitle("Customer");
					CustomerWindow.setScene(secondScene);

					stage.centerOnScreen();

					CustomerWindow.show();

					cart.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							System.out.print("Show Cart");
						}
					});
/*					
					addToCart.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							System.out.print("Added to cart");
						}
					});
*/					
					secondbutton.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							label.setText("");
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
					stage.close();
					Button secondbutton = new Button();
					secondbutton.setText("Logout, Employee");

					StackPane secondaryLayout = new StackPane();
					secondaryLayout.getChildren().add(secondbutton);

					Scene secondScene = new Scene(secondaryLayout, 650, 450);
					
					// new window
					Stage EmployeeWindow = new Stage();
					EmployeeWindow.setTitle("Employee");
					EmployeeWindow.setScene(secondScene);

					stage.centerOnScreen();

					EmployeeWindow.show();

					secondbutton.setOnAction(new EventHandler<ActionEvent>() {
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