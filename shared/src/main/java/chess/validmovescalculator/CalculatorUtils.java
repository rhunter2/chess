package chess.validmovescalculator;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.HashSet;

public class CalculatorUtils {

    private static final int BOARD_HEIGHT = 8;
    private static final int BOARD_WIDTH = 8;

    // Common helper method to process moves
    private static boolean processMove(ChessBoard board, ChessPosition start, ChessPosition target, ChessGame.TeamColor color, HashSet<ChessMove> moves) {
        var pieceAtTarget = board.getPiece(target);
        if (pieceAtTarget == null) {
            moves.add(new ChessMove(start, target, null)); // Empty square, valid move
            return true; // Continue processing
        } else if (pieceAtTarget.getTeamColor() != color) {
            moves.add(new ChessMove(start, target, null)); // Capture opponent's piece
            return false; // Stop after capture
        }
        return false; // Stop when encountering a friendly piece
    }

    public static void calculateUpperLeftMoves(ChessBoard board, ChessPosition position, ChessGame.TeamColor color, HashSet<ChessMove> moves, int limit) {
        int row = position.getRow();
        int col = position.getColumn();
        int count = 0;

        for (int i = row - 1, j = col - 1; i >= 1 && j >= 1; i--, j--) {
            if (count == limit) break;
            ChessPosition target = new ChessPosition(i, j);
            if (!processMove(board, position, target, color, moves)) break;
            count++;
        }
    }

    public static void calculateUpperRightMoves(ChessBoard board, ChessPosition position, ChessGame.TeamColor color, HashSet<ChessMove> moves, int limit) {
        int row = position.getRow();
        int col = position.getColumn();
        int count = 0;

        for (int i = row - 1, j = col + 1; i >= 1 && j <= BOARD_WIDTH; i--, j++) {
            if (count == limit) break;
            ChessPosition target = new ChessPosition(i, j);
            if (!processMove(board, position, target, color, moves)) break;
            count++;
        }
    }

    public static void calculateLowerLeftMoves(ChessBoard board, ChessPosition position, ChessGame.TeamColor color, HashSet<ChessMove> moves, int limit) {
        int row = position.getRow();
        int col = position.getColumn();
        int count = 0;

        for (int i = row + 1, j = col - 1; i <= BOARD_HEIGHT && j >= 1; i++, j--) {
            if (count == limit) break;
            ChessPosition target = new ChessPosition(i, j);
            if (!processMove(board, position, target, color, moves)) break;
            count++;
        }
    }

    public static void calculateLowerRightMoves(ChessBoard board, ChessPosition position, ChessGame.TeamColor color, HashSet<ChessMove> moves, int limit) {
        int row = position.getRow();
        int col = position.getColumn();
        int count = 0;

        for (int i = row + 1, j = col + 1; i <= BOARD_HEIGHT && j <= BOARD_WIDTH; i++, j++) {
            if (count == limit) break;
            ChessPosition target = new ChessPosition(i, j);
            if (!processMove(board, position, target, color, moves)) break;
            count++;
        }
    }

    public static void calculateLeftMoves(ChessBoard board, ChessPosition position, ChessGame.TeamColor color, HashSet<ChessMove> moves, int limit) {
        int row = position.getRow();
        int col = position.getColumn();
        int count = 0;

        for (int j = col - 1; j >= 1; j--) {
            if (count == limit) break;
            ChessPosition target = new ChessPosition(row, j);
            if (!processMove(board, position, target, color, moves)) break;
            count++;
        }
    }

    public static void calculateRightMoves(ChessBoard board, ChessPosition position, ChessGame.TeamColor color, HashSet<ChessMove> moves, int limit) {
        int row = position.getRow();
        int col = position.getColumn();
        int count = 0;

        for (int j = col + 1; j <= BOARD_WIDTH; j++) {
            if (count == limit) break;
            ChessPosition target = new ChessPosition(row, j);
            if (!processMove(board, position, target, color, moves)) break;
            count++;
        }
    }

    public static void calculateUpwardMoves(ChessBoard board, ChessPosition position, ChessGame.TeamColor color, HashSet<ChessMove> moves, int limit) {
        int row = position.getRow();
        int col = position.getColumn();
        int count = 0;

        for (int i = row - 1; i >= 1; i--) {
            if (count == limit) break;
            ChessPosition target = new ChessPosition(i, col);
            if (!processMove(board, position, target, color, moves)) break;
            count++;
        }
    }

    public static void calculateDownwardMoves(ChessBoard board, ChessPosition position, ChessGame.TeamColor color, HashSet<ChessMove> moves, int limit) {
        int row = position.getRow();
        int col = position.getColumn();
        int count = 0;

        for (int i = row + 1; i <= BOARD_HEIGHT; i++) {
            if (count == limit) break;
            ChessPosition target = new ChessPosition(i, col);
            if (!processMove(board, position, target, color, moves)) break;
            count++;
        }
    }
}
