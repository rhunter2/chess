package chess.validmovescalculator;


import chess.*;

import java.util.Collection;
import java.util.HashSet;

public class KingValidMovesCalculator implements PieceMovesCalculator {

    /**
     * Calculates the valid moves for the King on the board.
     * The King can move in any direction but only by one square.
     *
     * @param board    The current chessboard.
     * @param position The current position of the King.
     * @return A collection of valid moves for the King.
     */
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ChessGame.TeamColor color = board.getPiece(position).getTeamColor();
        HashSet<ChessMove> validMoves = new HashSet<>();

        // King moves one square in each direction
        CalculatorUtils.calculateUpwardMoves(board, position, color, validMoves, 1);
        CalculatorUtils.calculateDownwardMoves(board, position, color, validMoves, 1);
        CalculatorUtils.calculateLeftMoves(board, position, color, validMoves, 1);
        CalculatorUtils.calculateRightMoves(board, position, color, validMoves, 1);

        // Diagonal moves (upper-left, upper-right, lower-left, lower-right)
        CalculatorUtils.calculateUpperLeftMoves(board, position, color, validMoves, 1);
        CalculatorUtils.calculateUpperRightMoves(board, position, color, validMoves, 1);
        CalculatorUtils.calculateLowerLeftMoves(board, position, color, validMoves, 1);
        CalculatorUtils.calculateLowerRightMoves(board, position, color, validMoves, 1);

        return validMoves;
    }
}