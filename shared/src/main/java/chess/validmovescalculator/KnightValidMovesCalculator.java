package chess.validmovescalculator;

import chess.*;

import java.util.Collection;
import java.util.HashSet;

public class KnightValidMovesCalculator implements PieceMovesCalculator {

    private static final int[][] KNIGHT_MOVES = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, // Vertical moves (top and bottom)
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}  // Horizontal moves (left and right)
    };

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ChessGame.TeamColor color = board.getPiece(position).getTeamColor();
        HashSet<ChessMove> validMoves = new HashSet<>();
        int row = position.getRow();
        int col = position.getColumn();

        // Iterate through all possible knight moves
        for (int[] offset : KNIGHT_MOVES) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];

            // Ensure the new position is within bounds
            if (isWithinBounds(newRow, newCol)) {
                ChessPosition newPosition = new ChessPosition(newRow, newCol);
                ChessPiece pieceAtTarget = board.getPiece(newPosition);

                // Add move if the target square is either empty or has an opponent's piece
                if (pieceAtTarget == null || pieceAtTarget.getTeamColor() != color) {
                    validMoves.add(new ChessMove(position, newPosition, null));
                }
            }
        }

        return validMoves;
    }

    // Helper method to check if a position is within board bounds
    private boolean isWithinBounds(int row, int col) {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }
}