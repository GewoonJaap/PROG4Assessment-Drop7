package controller;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Ball;
import model.Game;
import view.GameView;

public class GameController extends Thread {

	private GameView gameView;
	private Stage main;
	private Game game;
	private Thread t;
	private boolean loop = true;
	private int LOOP_TIME = 2000;
	private int CURRENT_LOOP_TIME;
	private boolean cheatmode = false;

	public GameController(Stage main) {
		this.main = main;
		loadGame();
	}

	public void loadGame() {
		game = null;
		gameView = null;
		t = null;
		CURRENT_LOOP_TIME = 0;
		this.game = new Game();
		gameView = new GameView(this);
		if (main.getScene() != null) {
			main.setScene(new Scene(gameView, 720, 720));
			ready();
		}
	}

	public void ready() {
		this.start();
		main.getScene().setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.C) {
				cheatmode = !cheatmode;
				gameView.getScoreController().updateScoreView();
			} else if (cheatmode) {
				if (e.getCode() == KeyCode.DIGIT1) {
					game.setNextBallFixed(new Ball(1, "/resources/1.png"));
				} else if (e.getCode() == KeyCode.DIGIT2) {
					game.setNextBallFixed(new Ball(2, "/resources/2.png"));
				} else if (e.getCode() == KeyCode.DIGIT3) {
					game.setNextBallFixed(new Ball(3, "/resources/3.png"));
				} else if (e.getCode() == KeyCode.DIGIT4) {
					game.setNextBallFixed(new Ball(4, "/resources/4.png"));
				} else if (e.getCode() == KeyCode.DIGIT5) {
					game.setNextBallFixed(new Ball(5, "/resources/5.png"));
				} else if (e.getCode() == KeyCode.DIGIT6) {
					game.setNextBallFixed(new Ball(6, "/resources/6.png"));
				} else if (e.getCode() == KeyCode.DIGIT7) {
					game.setNextBallFixed(new Ball(7, "/resources/7.png"));
				}
				gameView.getScoreController().updateScoreView();
			}
		});
	}

	public boolean cheatmodeActivated() {
		return cheatmode;
	}

	public GameView getGameView() {
		return gameView;
	}

	public Game getGame() {
		return game;
	}

	public void run() {
		while (loop && !interrupted()) {
			System.out.println("Loop");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			CURRENT_LOOP_TIME += 100;
			if(CURRENT_LOOP_TIME >= LOOP_TIME) {
				System.out.println("Game OVER");
				boolean restartloop = loop;
				loop = false;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						showGameOver();
						loop = restartloop;
					}
				});
			}
		}
	}

	public void showGameOver() {
		gameView.showGameOver();
	}

	public void start() {
		System.out.println("start");
		CURRENT_LOOP_TIME = 0;
		if (t == null && loop) {
			t = new Thread(this);
			t.start();

		}
		
	}
	public void resetTimer() {
		CURRENT_LOOP_TIME = 0;
	}

}
