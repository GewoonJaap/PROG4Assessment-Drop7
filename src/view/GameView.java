package view;

import controller.BoardController;
import controller.GameController;
import controller.ScoreController;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameView extends BorderPane {

	private ScoreController scoreController;
	private ScoreView scoreView;
	private BoardController boardController;
	private BoardView boardView;
	private GameController gameController;
	private double WINDOW_HEIGHT = 720;
	private double WINDOW_WIDTH = 720;

	public GameView(GameController gameController) {
		this.gameController = gameController;
		drawView();
	}

	private void drawView() {
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setMinSize(WINDOW_WIDTH / 5, WINDOW_HEIGHT / 5);
		VBox vBox = new VBox(25.0);
		vBox.setMinSize(WINDOW_WIDTH / 5, WINDOW_HEIGHT / 5);

		scoreController = new ScoreController(this);
		scoreView = new ScoreView(scoreController);

		boardController = new BoardController();
		BoardView boardView = new BoardView(boardController);
		boardView.setMaxSize(448.0, 448.0);
		vBox.getChildren().addAll(scoreView, boardView);
		this.setCenter(vBox);

	}

	public GameController getGameController() {
		return gameController;
	}

}
