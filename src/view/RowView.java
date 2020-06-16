package view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import model.Row;

public class RowView extends BorderPane {

	private Row row;
	private BallView ballView;

	public RowView() {
		this.row = row = new Row(null);
		drawView();
	}

	private void drawView() {
		this.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		if (row.getBall() != null) {
			addBall();
		}
		this.setMinSize(64.0, 64.0);
		this.resize(64.0, 64.0);
	}

	private void addBall() {
		ballView = new BallView(row.getBall());
		this.setCenter(ballView);

	}

}
