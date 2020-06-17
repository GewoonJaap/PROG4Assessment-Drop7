package controller;

import javafx.stage.Stage;
import model.Ball;
import model.Game;
import model.Row;
import view.GameView;

public class GameController {

	private GameView gameView;
	private Stage main;
	private Game game;

	public GameController(Stage main) {
		this.main = main;
		this.game = new Game();
		gameView = new GameView(this);
		startGame();
	}
	
	private void startGame() {
		
	}

	public GameView getGameView() {
		return gameView;
	}

	public Game getGame() {
		return game;
	}

}
