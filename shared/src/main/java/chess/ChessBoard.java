package chess;
import java.util.HashMap;
import java.util.Map;
import chess.ChessPosition;
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
        board.put(new ChessPosition(0, 0), new Rook(ChessPiece.Color.BLACK));
        board.put(new ChessPosition(0, 1), new Knight(ChessPiece.Color.BLACK));
        board.put(new ChessPosition(0, 2), new Bishop(ChessPiece.Color.BLACK));
        board.put(new ChessPosition(0, 3), new Queen(ChessPiece.Color.BLACK));
        board.put(new ChessPosition(0, 4), new King(ChessPiece.Color.BLACK));
        board.put(new ChessPosition(0, 5), new Bishop(ChessPiece.Color.BLACK));
        board.put(new ChessPosition(0, 6), new Knight(ChessPiece.Color.BLACK));
        board.put(new ChessPosition(0, 7), new Rook(ChessPiece.Color.BLACK));

        // Black pawns
        for (int col = 0; col < 8; col++) {
            board.put(new ChessPosition(1, col), new Pawn(ChessPiece.Color.BLACK));
        }

        // White pieces
        board.put(new ChessPosition(7, 0), new Rook(ChessPiece.Color.WHITE));
        board.put(new ChessPosition(7, 1), new Knight(ChessPiece.Color.WHITE));
        board.put(new ChessPosition(7, 2), new Bishop(ChessPiece.Color.WHITE));
        board.put(new ChessPosition(7, 3), new Queen(ChessPiece.Color.WHITE));
        board.put(new ChessPosition(7, 4), new King(ChessPiece.Color.WHITE));
        board.put(new ChessPosition(7, 5), new Bishop(ChessPiece.Color.WHITE));
        board.put(new ChessPosition(7, 6), new Knight(ChessPiece.Color.WHITE));
        board.put(new ChessPosition(7, 7), new Rook(ChessPiece.Color.WHITE));

        // White pawns
        for (int col = 0; col < 8; col++) {
            board.put(new ChessPosition(6, col), new Pawn(ChessPiece.Color.WHITE));
        }
        private boolean isValidPosition(ChessPosition position) {
            return position.getRow() >= 0 && position.getRow() < 8
                    && position.getColumn() >= 0 && position.getColumn() < 8;
        }

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
