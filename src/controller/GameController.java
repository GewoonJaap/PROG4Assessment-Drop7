package controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import model.Game;
import view.GameView;

public class GameController extends Thread {

	private GameView gameView;
	private Stage main;
	private Game game;
	private Thread t;
	private boolean loop = true;
	private int LOOP_TIME = 2000;

	public GameController(Stage main) {
		this.main = main;
		this.game = new Game();
		startGame();
	}

	private void startGame() {
		gameView = new GameView(this);
		this.start();
	}

	public GameView getGameView() {
		return gameView;
	}

	public Game getGame() {
		return game;
	}

	public void run() {
		while (loop) {
			int ballLeftSave = game.getBallLeft();
			System.out.println("Loop");
			try {
				Thread.sleep(LOOP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (game.getBallLeft() == ballLeftSave) {
				System.out.println("Game OVER");
				loop = false;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						showGameOver();
					}
				});
			}
		}
	}

	public void showGameOver() {
		gameView.showGameOver();
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

}
