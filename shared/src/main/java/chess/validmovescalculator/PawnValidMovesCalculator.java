package chess.validmovescalculator;

import chess.*;

import java.util.Collection;
import java.util.HashSet;

public class PawnValidMovesCalculator implements PieceMovesCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ChessGame.TeamColor color = board.getPiece(position).getTeamColor();
        HashSet<ChessMove> validMoves = new HashSet<>();
        int direction = (color == ChessGame.TeamColor.WHITE) ? 1 : -1; // White moves up, Black moves down
        int startRow = (color == ChessGame.TeamColor.WHITE) ? 2 : 7;    // Starting row for double move
        int promotionRow = (color == ChessGame.TeamColor.WHITE) ? 8 : 1; // Row for promotion

        // Check forward moves
        calculateForwardMoves(board, position, validMoves, direction, startRow, promotionRow);

        // Check diagonal captures (left and right)
        calculateDiagonalCapture(board, position, validMoves, direction, promotionRow);

        return validMoves;
    }

    private void calculateForwardMoves(ChessBoard board, ChessPosition position, HashSet<ChessMove> validMoves, int direction, int startRow, int promotionRow) {
        int row = position.getRow();
        int col = position.getColumn();

        // Single forward move
        ChessPosition forwardPosition = new ChessPosition(row + direction, col);
        if (board.getPiece(forwardPosition) == null) {
            addMoveWithPromotionCheck(position, forwardPosition, validMoves, row + direction == promotionRow);
        }

        // Double forward move if on the starting row
        if (row == startRow) {
            ChessPosition doubleForwardPosition = new ChessPosition(row + 2 * direction, col);
            if (board.getPiece(forwardPosition) == null && board.getPiece(doubleForwardPosition) == null) {
                validMoves.add(new ChessMove(position, doubleForwardPosition, null));
            }
        }
    }

    private void calculateDiagonalCapture(ChessBoard board, ChessPosition position, HashSet<ChessMove> validMoves, int direction, int promotionRow) {
        int row = position.getRow();
        int col = position.getColumn();

        // Left diagonal capture
        if (col > 1) {
            ChessPosition leftDiagonal = new ChessPosition(row + direction, col - 1);
            if (isOpponentPiece(board, position, leftDiagonal)) {
                addMoveWithPromotionCheck(position, leftDiagonal, validMoves, row + direction == promotionRow);
            }
        }

        // Right diagonal capture
        if (col < 8) {
            ChessPosition rightDiagonal = new ChessPosition(row + direction, col + 1);
            if (isOpponentPiece(board, position, rightDiagonal)) {
                addMoveWithPromotionCheck(position, rightDiagonal, validMoves, row + direction == promotionRow);
            }
        }
    }

    private boolean isOpponentPiece(ChessBoard board, ChessPosition currentPosition, ChessPosition targetPosition) {
        ChessPiece targetPiece = board.getPiece(targetPosition);
        return targetPiece != null && targetPiece.getTeamColor() != board.getPiece(currentPosition).getTeamColor();
    }

    private void addMoveWithPromotionCheck(ChessPosition start, ChessPosition end, HashSet<ChessMove> validMoves, boolean isPromotion) {
        if (isPromotion) {
            validMoves.add(new ChessMove(start, end, ChessPiece.PieceType.QUEEN));
            validMoves.add(new ChessMove(start, end, ChessPiece.PieceType.BISHOP));
            validMoves.add(new ChessMove(start, end, ChessPiece.PieceType.ROOK));
            validMoves.add(new ChessMove(start, end, ChessPiece.PieceType.KNIGHT));
        } else {
            validMoves.add(new ChessMove(start, end, null));
        }
    }
}