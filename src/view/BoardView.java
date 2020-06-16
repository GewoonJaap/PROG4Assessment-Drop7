package view;

import controller.BoardController;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class BoardView extends BorderPane {

	private RowView[][] rowView;
	private int ROWS_Y = 7;
	private int ROWS_X = 7;

	public BoardView(BoardController boardController) {
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		drawView();
	}

	private void drawView() {
		rowView = new RowView[ROWS_Y][ROWS_X];

		VBox vBox = new VBox(1.0);
		for (int y = 0; y < ROWS_Y; y++) {
			HBox hbox = new HBox(1.0);

			for (int x = 0; x < ROWS_X; x++) {
				rowView[y][x] = new RowView();
				hbox.getChildren().add(rowView[y][x]);
			}
			vBox.getChildren().add(hbox);
		}
		this.setCenter(vBox);
	}

}
