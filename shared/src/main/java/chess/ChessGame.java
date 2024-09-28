package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * Manages a chess game, including making moves and determining game states.
 */
public class ChessGame {

    private TeamColor currentTurn;
    private ChessBoard board;

    public ChessGame() {
        this.board = new ChessBoard();
        this.currentTurn = TeamColor.WHITE;
    }

    public TeamColor getTeamTurn() {
        return this.currentTurn;
    }

    public void setTeamTurn(TeamColor team) {
        this.currentTurn = team;
    }

    public enum TeamColor {
        WHITE,
        BLACK
    }

    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = board.getPiece(startPosition);
        if (piece == null || piece.getTeamColor() != this.currentTurn) {
            return new HashSet<>();  // No valid moves if there's no piece or it's not the player's turn
        }

        Collection<ChessMove> possibleMoves = piece.pieceMoves(this.board, startPosition);
        Collection<ChessMove> validMoves = new HashSet<>();

        for (ChessMove move : possibleMoves) {
            if (isValidMoveAfterCheck(move, startPosition, piece)) {
                validMoves.add(move);
            }
        }
        return validMoves;
    }

    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece piece = board.getPiece(move.getStartPosition());

        if (!validMoves(move.getStartPosition()).contains(move)) {
            throw new InvalidMoveException("Invalid move");
        }

        executeMove(move, piece);
        switchTurn();
    }

    public boolean isInCheck(TeamColor teamColor) {
        return isKingInCheck(board, teamColor);
    }

    public boolean isInCheckmate(TeamColor teamColor) {
        return isInCheck(teamColor) && noValidMovesAvailable(teamColor);
    }

    public boolean isInStalemate(TeamColor teamColor) {
        return !isInCheck(teamColor) && noValidMovesAvailable(teamColor);
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    public ChessBoard getBoard() {
        return this.board;
    }

    // Private helper methods

    private boolean isValidMoveAfterCheck(ChessMove move, ChessPosition startPosition, ChessPiece piece) {
        try {
            ChessBoard boardCopy = cloneBoardWithMove(move, startPosition, piece);
            return !isKingInCheck(boardCopy, piece.getTeamColor());
        } catch (CloneNotSupportedException | InvalidMoveException e) {
            throw new RuntimeException(e);
        }
    }

    private ChessBoard cloneBoardWithMove(ChessMove move, ChessPosition startPosition, ChessPiece piece) throws CloneNotSupportedException, InvalidMoveException {
        ChessBoard boardCopy = (ChessBoard) this.board.clone();
        boardCopy.addPiece(move.getEndPosition(), move.getPromotionPiece() == null ? piece : new ChessPiece(currentTurn, move.getPromotionPiece()));
        boardCopy.removePiece(startPosition, piece);
        return boardCopy;
    }

    private void executeMove(ChessMove move, ChessPiece piece) throws InvalidMoveException {
        if (move.getPromotionPiece() == null) {
            board.addPiece(move.getEndPosition(), piece);
        } else {
            board.addPiece(move.getEndPosition(), new ChessPiece(currentTurn, move.getPromotionPiece()));
        }
        board.removePiece(move.getStartPosition(), piece);
    }

    private void switchTurn() {
        this.currentTurn = (this.currentTurn == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;
    }

    private boolean isKingInCheck(ChessBoard board, TeamColor teamColor) {
        ChessPosition kingPosition = findKingPosition(board, teamColor);
        if (kingPosition == null) return false;

        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPiece piece = board.getPiece(new ChessPosition(row, col));
                if (piece != null && piece.getTeamColor() != teamColor) {
                    Collection<ChessMove> moves = piece.pieceMoves(board, new ChessPosition(row, col));
                    for (ChessMove move : moves) {
                        if (move.getEndPosition().equals(kingPosition)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean noValidMovesAvailable(TeamColor teamColor) {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPosition position = new ChessPosition(row, col);
                ChessPiece piece = this.board.getPiece(position);
                if (piece != null && piece.getTeamColor() == teamColor && !validMoves(position).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private ChessPosition findKingPosition(ChessBoard board, TeamColor teamColor) {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                ChessPiece piece = board.getPiece(new ChessPosition(row, col));
                if (piece != null && piece.getPieceType() == ChessPiece.PieceType.KING && piece.getTeamColor() == teamColor) {
                    return new ChessPosition(row, col);
                }
            }
        }
        return null;
    }
}
