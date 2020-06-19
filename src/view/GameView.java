package view;

import controller.BoardController;
import controller.GameController;
import controller.ScoreController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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
		this.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		VBox vBox = new VBox(25.0);
		vBox.setMinSize(WINDOW_WIDTH / 5, WINDOW_HEIGHT / 5);

		scoreController = new ScoreController(this);
		scoreView = new ScoreView(scoreController);

		boardController = new BoardController(this);
		BoardView boardView = new BoardView(boardController);
		int sizeX = (gameController.getGame().getFieldSizeX() + 1 * gameController.getGame().getRowSizeX());
		int sizeY = (gameController.getGame().getFieldSizeY() + 1 * gameController.getGame().getRowSizeY());
		boardView.setMaxSize(sizeX, sizeY);
		vBox.getChildren().addAll(scoreView, boardView);
		this.setCenter(vBox);

	}

	public void showGameOver() {
		this.getChildren().clear();
		Label gameOver = new Label("GAME OVER");
		this.setCenter(gameOver);
	}

	public GameController getGameController() {
		return gameController;
	}

	public BoardController getBoardController() {
		return boardController;
	}

	public ScoreController getScoreController() {
		return scoreController;
	}

}
