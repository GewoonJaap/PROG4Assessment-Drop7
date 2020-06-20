import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private int WINDOW_WIDTH = 720;
	private int WINDOW_HEIGHT = 720;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		GameController gameController = new GameController(stage);
		Scene scene = new Scene(gameController.getGameView(), WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.setTitle("PROG ASS – Drop7 - Jaap Rodenburg");
		stage.setResizable(false);
		stage.show();
		gameController.ready();

	}

}
