package controller;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
	private int LOOP_SPEED = 10;
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
				// Flip-flop the cheatmode
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
		// Timer loop
		// Check if it can loop
		while (loop && !interrupted()) {
			System.out.println("Loop");
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					// Update timeleft in another Thread, otherwise JavaFX doesn't allow it...
					gameView.getScoreController().updateTimeLeft();
				}
			});

			try {
				// Wait for a few mili seconds to save some CPU cycles before checking if time
				// is over..
				Thread.sleep(LOOP_SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			CURRENT_LOOP_TIME += LOOP_SPEED;
			// Add looped time to current loop time
			// Check if the loop time if bigger or equel to the max loop time.
			if (CURRENT_LOOP_TIME >= LOOP_TIME) {
				// Game is over
				System.out.println("Game OVER");
				boolean restartloop = loop;
				loop = false;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// Show game over in anoother Thread, otherwise JavaFX doesn't allow it..
						showGameOver();
						// Stop the loop
						loop = restartloop;
					}
				});
			}
		}
	}

	public void showGameOver() {
		// Show gameOver in the game view
		gameView.showGameOver();
	}

	public void start() {
		// Start the loop
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

	public int getCurrentLoopTime() {
		return CURRENT_LOOP_TIME;
	}

	public int getLoopTime() {
		return LOOP_TIME;
	}

}
