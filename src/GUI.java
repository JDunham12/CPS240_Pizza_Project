import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class GUI extends Application {
	@Override
	public void start(final Stage primaryStage) {

		Button button = new Button();
		button.setText("Login");

		
		
		//Change this so it only works when correct user is input
		button.setOnAction(new EventHandler<ActionEvent>() {
////////////////////////////////////////////////////////////////////////////////////////////
			@Override
			public void handle(ActionEvent event) {

				primaryStage.close();
				Button secondbutton = new Button("Button");
				secondbutton.setText("Logout");

				StackPane secondaryLayout = new StackPane();
				secondaryLayout.getChildren().add(secondbutton);
				

				Scene secondScene = new Scene(secondaryLayout, 650, 450);

				//new window
				Stage newWindow = new Stage();
				newWindow.setTitle("Customer");
				newWindow.setScene(secondScene);

				primaryStage.centerOnScreen();

				newWindow.show();
				
				/////////////////////////////////////////////////////////////////////////////
				secondbutton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						newWindow.close();
						primaryStage.show();
					}
				});
				////////////////////////////////////////////////////////////////////////
			}
		});
		//////////////////////////////////////////////////////////////////////////////////
		
		StackPane root = new StackPane();
		root.getChildren().add(button);

		Scene scene = new Scene(root, 650, 450);
		
		primaryStage.centerOnScreen();
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}