package chess.validmovescalculator;

import chess.*;
import java.util.Collection;
import java.util.HashSet;

public class RookValidMovesCalculator implements PieceMovesCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ChessGame.TeamColor color = board.getPiece(position).getTeamColor();
        HashSet<ChessMove> validMoves = new HashSet<>();

        // Rook can move in straight lines (up, down, left, right)
        CalculatorUtils.calculateUpwardMoves(board, position, color, validMoves, 8);    // Upward direction
        CalculatorUtils.calculateDownwardMoves(board, position, color, validMoves, 8);  // Downward direction
        CalculatorUtils.calculateLeftMoves(board, position, color, validMoves, 8);      // Left direction
        CalculatorUtils.calculateRightMoves(board, position, color, validMoves, 8);     // Right direction

        return validMoves;
    }
}
