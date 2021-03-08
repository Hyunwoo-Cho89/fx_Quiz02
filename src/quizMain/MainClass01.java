package quizMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass01 extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("surveyInput.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		Controller01 controller = loader.getController();
		controller.setRoot(root);
		
		arg0.setScene(scene);
		arg0.show();
	}
	public static void main(String[] args) {
		
		launch(args);
	}

}
