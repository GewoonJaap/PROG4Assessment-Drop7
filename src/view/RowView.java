package view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import model.Row;

public class RowView extends BorderPane {

	private Row row;
	private BallView ballView;
	private int WIDTH;
	private int HEIGHT;
	private Color backgroundColor = Color.BLUE;

	public RowView(Row row) {
		// Create a row
		this.row = row;
		this.WIDTH = row.getBoardController().getGame().getRowSizeX();
		this.HEIGHT = row.getBoardController().getGame().getRowSizeY();
		drawView();
		this.setOnMouseClicked(e -> row.getBoardController().clickedRow(row));
	}

	public void setRow(Row row) {
		// Update the row for the view
		this.row = row;
		drawView();
	}

	private void drawView() {
		// Draw the row
		this.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
		try {
			// Add a ball
			if (row.getBall() != null) {
				addBall();
			}
		} catch (NullPointerException e) {
		}
		this.setMinSize(WIDTH, HEIGHT);
		this.setMaxSize(WIDTH, HEIGHT);
	}

	private void addBall() {
		ballView = new BallView(row.getBall());
		this.setCenter(ballView);

	}

}
