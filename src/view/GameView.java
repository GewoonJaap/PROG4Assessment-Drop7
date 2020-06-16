package view;

import controller.BoardController;
import controller.GameController;
import controller.ScoreController;
import javafx.scene.layout.BorderPane;

public class GameView extends BorderPane {

	private ScoreController scoreController;
	private ScoreView scoreView;
	private BoardController boardController;
	private BoardView boardView;
	private GameController gameController;

	public GameView(GameController gameController) {
		this.gameController = gameController;
		drawView();
	}

	private void drawView() {
		scoreController = new ScoreController(this);
		scoreView = new ScoreView(scoreController);

		boardController = new BoardController();
		BoardView boardView = new BoardView(boardController);

		this.getChildren().addAll(scoreView, boardView);

	}

	public GameController getGameController() {
		return gameController;
	}

}
