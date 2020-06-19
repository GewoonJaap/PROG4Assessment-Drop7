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
	private Row[][] row;
	private int ROWS_Y;
	private int ROWS_X;
	private double SQUARE_SPACING = 1.0;
	private BoardController boardController;

	public BoardView(BoardController boardController) {
		boardController.setBoardView(this);
		this.boardController = boardController;
		ROWS_X = boardController.getGame().getFieldSizeX() + 1;
		ROWS_Y = boardController.getGame().getFieldSizeY() + 1;
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		drawView(false);
	}

	private void drawView(boolean reDraw) {
		this.getChildren().clear();
		if (!reDraw) {
			rowView = new RowView[ROWS_Y][ROWS_X];
			row = new Row[ROWS_Y][ROWS_X];
		}

		VBox vBox = new VBox(SQUARE_SPACING);
		for (int y = 0; y < ROWS_Y; y++) {
			HBox hbox = new HBox(SQUARE_SPACING);

			for (int x = 0; x < ROWS_X; x++) {
				Row newRow = new Row(null, x, y, boardController);
				if (!reDraw) {
					row[y][x] = newRow;
				}
				rowView[y][x] = new RowView(row[y][x]);
				hbox.getChildren().add(rowView[y][x]);
			}
			vBox.getChildren().add(hbox);
		}
		this.setCenter(vBox);
	}

	public void addRow(Row row) {
		this.row[row.getY()][row.getX()] = row;
		drawView(true);
	}

	public RowView[][] getRowView() {
		return rowView;
	}

	public Row[][] getRows() {
		return row;
	}

}
