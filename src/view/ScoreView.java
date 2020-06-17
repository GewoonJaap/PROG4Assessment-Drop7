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
	private BallView nextBall;
	private VBox vBox;

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
		if (scoreController.getNextBall() != null) {
			nextBall = new BallView(scoreController.getNextBall());
		}
		vBox = new VBox(3.0);
		if (scoreController.getNextBall() == null) {
			vBox.getChildren().addAll(score, level, ballLeft);
		} else {
			vBox.getChildren().addAll(score, level, ballLeft, nextBall);
		}
		this.setCenter(vBox);

	}

}
