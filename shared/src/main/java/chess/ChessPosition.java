package chess;

import java.util.Objects;

/**
 * Represents a single square position on a chess board.
 * The row and column are 1-indexed, where 1 refers to the bottom row and left-most column respectively.
 */
public class ChessPosition {

    private final int row;
    private final int col;

    /**
     * Constructs a ChessPosition object with the given row and column.
     *
     * @param row the row number (1 for the bottom row)
     * @param col the column number (1 for the left-most column)
     */
    public ChessPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @return the row number of this position (1-indexed)
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the column number of this position (1-indexed)
     */
    public int getColumn() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPosition)) return false;
        ChessPosition that = (ChessPosition) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return String.format("ChessPosition{row=%d, col=%d}", row, col);
    }
}
