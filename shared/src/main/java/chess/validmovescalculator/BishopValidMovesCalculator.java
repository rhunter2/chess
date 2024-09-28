package chess.validmovescalculator;

import chess.*;

import java.util.Collection;
import java.util.HashSet;

public class BishopValidMovesCalculator implements PieceMovesCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ChessGame.TeamColor color = board.getPiece(position).getTeamColor();
        HashSet<ChessMove> validMoves = new HashSet<>();

        // Check upper-left diagonal
        CalculatorUtils.calculateUpperLeftMoves(board, position, color, validMoves, 8);

        // Check upper-right diagonal
        CalculatorUtils.calculateUpperRightMoves(board, position, color, validMoves, 8);

        // Check lower-left diagonal
        CalculatorUtils.calculateLowerLeftMoves(board, position, color, validMoves, 8);

        // Check lower-right diagonal
        CalculatorUtils.calculateLowerRightMoves(board, position, color, validMoves, 8);

        return validMoves;
    }
}