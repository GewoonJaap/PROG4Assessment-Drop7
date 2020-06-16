package view;

import controller.ScoreController;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ScoreView extends BorderPane {

	private ScoreController scoreController;
	private Label score;
	private Label level;
	private BallView nextBall;
	private HBox hBox;

	public ScoreView(ScoreController scoreController) {
		this.scoreController = scoreController;
		createView();
	}

	private void createView() {
		score = new Label(Integer.toString(scoreController.getScore()));
		level = new Label(Integer.toString(scoreController.getLevel()));
		nextBall = new BallView(scoreController.getNextBall());
		hBox = new HBox(3.0);
		hBox.getChildren().addAll(score, level, nextBall);
		this.getChildren().add(hBox);
	}

}
