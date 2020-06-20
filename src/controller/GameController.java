package controller;

import javafx.application.Platform;
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
	private boolean loop = false;
	private int LOOP_TIME = 2000;
	private boolean cheatmode = false;

	public GameController(Stage main) {
		this.main = main;
		this.game = new Game();
		loadGame();
	}

	private void loadGame() {
		gameView = new GameView(this);
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
				}
				else if (e.getCode() == KeyCode.DIGIT2) {
					game.setNextBallFixed(new Ball(2, "/resources/2.png"));
				}
				else if (e.getCode() == KeyCode.DIGIT3) {
					game.setNextBallFixed(new Ball(3, "/resources/3.png"));
				}
				else if (e.getCode() == KeyCode.DIGIT4) {
					game.setNextBallFixed(new Ball(4, "/resources/4.png"));
				}
				else if (e.getCode() == KeyCode.DIGIT5) {
					game.setNextBallFixed(new Ball(5, "/resources/5.png"));
				}
				else if (e.getCode() == KeyCode.DIGIT6) {
					game.setNextBallFixed(new Ball(6, "/resources/6.png"));
				}
				else if (e.getCode() == KeyCode.DIGIT7) {
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
		if (t == null && loop) {
			t = new Thread(this);
			t.start();
		}
	}

}
