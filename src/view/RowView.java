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
	private ImageView ballImage;

	public RowView(Row row) {
		this.row = row;
		DrawView();
	}

	private void DrawView() {
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private void addBall() {
		ballImage.setImage(new Image(row.getBall().getImage()));
		this.setCenter(ballImage);

	}

}
