package model;

import java.util.Random;


public class Game {

	private int score;
	private int level;
	private Ball nextBall;
	private int ballLeft;
	private int BallLeft_START = 30;

	public Game() {
		score = 0;
		level = 1;
		ballLeft = BallLeft_START;
		setNextBall();
	}

	public void setNextBall() {
		removeBallLeft();
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

	public void addToScore(int score) {
		this.score += score;
	}

	private void removeBallLeft() {
		ballLeft--;
	}

	public int getBallLeft() {
		return ballLeft;
	}

	public void resetBallLeft() {
		ballLeft = BallLeft_START;
		setNextBall();
	}

	public void nextlevel() {
		level++;
	}

}
