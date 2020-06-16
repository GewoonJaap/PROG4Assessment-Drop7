package view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import model.Ball;
import model.Row;

public class RowView extends BorderPane {

	private Row row;
	private BallView ballView;
	private int WIDTH = 64;
	private int HEIGHT = 64;

	public RowView(Row row) {
		this.row = row;
		drawView();
	}

	private void drawView() {
		this.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		if (row.getBall() != null) {
			addBall();
		}
		this.setMinSize(WIDTH, HEIGHT);
		this.setMaxSize(WIDTH, HEIGHT);
	}

	private void addBall() {
		ballView = new BallView(row.getBall());
		this.setCenter(ballView);

	}

}
