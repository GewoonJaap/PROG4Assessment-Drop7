package controller;

import model.Ball;
import view.GameView;
import view.ScoreView;

public class ScoreController {

	private GameView gameView;
	private ScoreView scoreView;

	public ScoreController(GameView gameView) {
		this.gameView = gameView;
	}

	public void setScoreView(ScoreView scoreView) {
		this.scoreView = scoreView;
	}

	public void updateScoreView() {
		scoreView.createView();
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

	public GameView getGameView() {
		return gameView;
	}

}
