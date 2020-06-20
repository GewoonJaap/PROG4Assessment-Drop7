package view;

import controller.ScoreController;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Ball;

public class ScoreView extends BorderPane {

	private ScoreController scoreController;
	private Label score;
	private Label level;
	private Label ballLeft;
	private Label cheatmode;
	private BallView nextBall;
	private VBox vBox;
	private double vBox_SPACING = 3.0;

	public ScoreView(ScoreController scoreController) {
		this.scoreController = scoreController;
		scoreController.setScoreView(this);
		createView();
	}

	public void createView() {
		this.getChildren().clear();
		score = new Label("Score: " + Integer.toString(scoreController.getScore()));
		level = new Label("Level: " + Integer.toString(scoreController.getLevel()));
		ballLeft = new Label("Balls left: " + Integer.toString(scoreController.getBallLeft()));
		cheatmode = new Label("Cheat mode enabled: " +Boolean.toString(scoreController.getGameView().getGameController().cheatmodeActivated()) + " Press C to toggle");
		if (scoreController.getNextBall() != null) {
			nextBall = new BallView(scoreController.getNextBall());
		}
		vBox = new VBox(vBox_SPACING);
		if (scoreController.getNextBall() == null) {
			vBox.getChildren().addAll(score, level, ballLeft, cheatmode);
		} else {
			vBox.getChildren().addAll(score, level, ballLeft, cheatmode, nextBall);
		}
		this.setCenter(vBox);

	}

}
