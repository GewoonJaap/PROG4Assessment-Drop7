import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.GameView;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		GameView gameView = new GameView();
		Scene scene = new Scene(gameView , 720, 720);
		stage.setScene(scene);
		stage.setTitle("PROG 4 Assesment Jaap Rodenburg 2151202");
		stage.show();
		
	}

}
