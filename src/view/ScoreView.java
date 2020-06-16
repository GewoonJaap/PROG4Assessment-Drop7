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
	private BallView nextBall;
	private VBox vBox;

	public ScoreView(ScoreController scoreController) {
		this.scoreController = scoreController;
		createView();
	}

	private void createView() {
		score = new Label("Score: " + Integer.toString(scoreController.getScore()));
		level = new Label("Level: " + Integer.toString(scoreController.getLevel()));
		nextBall = new BallView(scoreController.getNextBall());
		vBox = new VBox(3.0);
		vBox.getChildren().addAll(score, level, nextBall);
		this.setCenter(vBox);
	}

}
