package chess.validmovescalculator;


import chess.*;
import java.util.Collection;
import java.util.HashSet;


import chess.*;
import java.util.Collection;
import java.util.HashSet;

public class QueenValidMovesCalculator implements PieceMovesCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ChessGame.TeamColor color = board.getPiece(position).getTeamColor();
        HashSet<ChessMove> validMoves = new HashSet<>();

        // Straight directions (up, down, left, right)
        CalculatorUtils.calculateUpwardMoves(board, position, color, validMoves, 8);
        CalculatorUtils.calculateDownwardMoves(board, position, color, validMoves, 8);
        CalculatorUtils.calculateLeftMoves(board, position, color, validMoves, 8);
        CalculatorUtils.calculateRightMoves(board, position, color, validMoves, 8);

        // Diagonal directions (upper-left, upper-right, lower-left, lower-right)
        CalculatorUtils.calculateUpperLeftMoves(board, position, color, validMoves, 8);
        CalculatorUtils.calculateUpperRightMoves(board, position, color, validMoves, 8);
        CalculatorUtils.calculateLowerLeftMoves(board, position, color, validMoves, 8);
        CalculatorUtils.calculateLowerRightMoves(board, position, color, validMoves, 8);

        return validMoves;
    }
}