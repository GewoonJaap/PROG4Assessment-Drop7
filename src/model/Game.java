package model;

import java.util.Random;

import view.BallView;

public class Game {

	private int score;
	private int level;
	private Ball nextBall;

	public Game() {
		score = 0;
		level = 1;
		setNextBall();
	}

	public void setNextBall() {
		int ballNr = (int) Math.floor(Math.random() * (1 - 7) + 7);
		Random random = new Random();
		// 25% chance for a full ball
		if ((random.nextInt(4) == 0)) {
			nextBall = new Ball(ballNr, "/resources/full.png");
			return;
		}
		nextBall = new Ball(ballNr, "/resources/" + ballNr + ".png");
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public Ball getNextBall() {
		return nextBall;
	}

	public void setNextBallFixed(Ball ball) {
		this.nextBall = ball;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
