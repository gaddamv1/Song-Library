package songlib.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import songlib.view.songlibController;

public class SongLib extends Application {

	@Override
	public void start(Stage primaryStage) 
	throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/songLibrary.fxml"));
		VBox root = (VBox) loader.load();
		
		songlibController listController = 
				loader.getController();
		listController.start(primaryStage);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Song Library");
		primaryStage.setResizable(false);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
		System.out.println("Done!");
	}

}