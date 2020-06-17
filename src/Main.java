import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		GameController gameController = new GameController(stage);
		Scene scene = new Scene(gameController.getGameView(), 720, 720);
		stage.setScene(scene);
		stage.setTitle("PROG ASS – Drop7 - Jaap Rodenburg");
		stage.setResizable(false);
		stage.show();

	}

}
