package eg.edu.guc.santorini.utilities;

import javax.swing.JLabel;

import eg.edu.guc.santorini.BoardInterface;

@SuppressWarnings("serial")
public class Location extends JLabel {

	private int column;
	private int row;

	public Location() {
		super();
	}

	public Location(int column, int row) {
		this.column = column;
		this.row = row;
	}

	public final int getColumn() {
		return column;
	}

	public final void setColumn(int column) {
		this.column = column;
	}

	public final int getRow() {
		return row;
	}

	public final void setRow(int row) {
		this.row = row;
	}

	public final boolean validLocation() {
		return row >= 0 && row < BoardInterface.SIDE && column >= 0
				&& column < BoardInterface.SIDE;
	}

	public boolean equals(Object another) {
		if (!(another instanceof Location)) {
			return false;
		}
		return row == ((Location) another).getRow()
				&& column == ((Location) another).getColumn();
	}

	public String toString() {
		return "(" + column + ", " + row + ")";
	}
}
