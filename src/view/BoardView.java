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
import model.Row;

public class BoardView extends BorderPane {

	private RowView[][] rowView;
	private int ROWS_Y;
	private int ROWS_X;
	private double SQUARE_SPACING = 2.0;
	private BoardController boardController;

	public BoardView(BoardController boardController) {
		// Setup the boardview
		boardController.setBoardView(this);
		this.boardController = boardController;
		ROWS_X = boardController.getGame().getFieldSizeX() + 1;
		ROWS_Y = boardController.getGame().getFieldSizeY() + 1;
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		// Create the view, not redraw it.
		drawView(false);
	}

	private void drawView(boolean reDraw) {
		Row[][] row = boardController.getGame().getRows();
		this.getChildren().clear();
		if (!reDraw) {
			rowView = new RowView[ROWS_Y][ROWS_X];
		}
		// Add rows
		VBox vBox = new VBox(SQUARE_SPACING);
		for (int y = 0; y < ROWS_Y; y++) {
			HBox hbox = new HBox(SQUARE_SPACING);

			for (int x = 0; x < ROWS_X; x++) {
				Row newRow = new Row(null, x, y, boardController);
				if (!reDraw) {
					// Create a new row and add it
					row[y][x] = newRow;
					boardController.getGame().setRows(row);
				}
				// Create a new rowView for the selected row
				rowView[y][x] = new RowView(row[y][x]);
				hbox.getChildren().add(rowView[y][x]);
			}
			vBox.getChildren().add(hbox);
		}
		this.setCenter(vBox);
	}

	public void addRow() {
		drawView(true);
	}

	public RowView[][] getRowView() {
		return rowView;
	}

}
