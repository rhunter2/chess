package chess.validmovescalculator;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;

public interface PieceMovesCalculator {
    // Method to calculate valid moves for a chess piece
    Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position);
}