package controller;

import model.Ball;
import view.GameView;

public class ScoreController {

	private GameView gameView;

	public ScoreController(GameView gameView) {
		this.gameView = gameView;
	}

	public int getScore() {
		return gameView.getGameController().getGame().getScore();
	}

	public int getLevel() {
		return gameView.getGameController().getGame().getLevel();
	}

	public Ball getNextBall() {
		return gameView.getGameController().getGame().getNextBall();
	}

}
