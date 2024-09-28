package chess;
import java.util.HashMap;
import java.util.Map;
/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private Map<ChessPosition, ChessPiece> board;

    public ChessBoard() {
        board = new  HashMap<>();

    }

    /**
     * Adds a chess piece to the chessboard * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        if (position != null && piece != null && isValidPosition(position)) {
            board.put(position,piece);
        }
    }

    /**
     * Gets a chess piece on the chessboard
     * @param position The position to get the piece from @return Either the piece at the position, or null if no piece is at that position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board.get(position);
    }

    /**
     * Sets the board to the default starting board (How the game of chess normally starts)
     */
    public void resetBoard() {
        board.clear(); // Clear the board first

        // Black pieces
        board.put(new ChessPosition(0, 0), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        board.put(new ChessPosition(0, 1), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        board.put(new ChessPosition(0, 2), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        board.put(new ChessPosition(0, 3), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
        board.put(new ChessPosition(0, 4), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
        board.put(new ChessPosition(0, 5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        board.put(new ChessPosition(0, 6), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        board.put(new ChessPosition(0, 7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));

        // Black pawns
        for (int col = 0; col < 8; col++) {
            board.put(new ChessPosition(1, col), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        }

        // White pieces
        board.put(new ChessPosition(7, 0), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        board.put(new ChessPosition(7, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        board.put(new ChessPosition(7, 2), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        board.put(new ChessPosition(7, 3), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
        board.put(new ChessPosition(7, 4), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        board.put(new ChessPosition(7, 5), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        board.put(new ChessPosition(7, 6), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        board.put(new ChessPosition(7, 7), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));

        // White pawns
        for (int col = 0; col < 8; col++) {
            board.put(new ChessPosition(6, col), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        }


    }
    public boolean isValidPosition(ChessPosition position) {
        return position.getRow() >= 0 && position.getRow() < 8
                && position.getColumn() >= 0 && position.getColumn() < 8;
    }
    /**
     * Removes a piece from a specific position (for capturing moves)
     *
     * @param position The position to remove the piece from
     */
    public void removePiece(ChessPosition position) {
        board.remove(position);
    }

}
