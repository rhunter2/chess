package chess;

/**
 * Represents moving a chess piece on a chessboard.
 * <p>
 * Note: You can add to this class, but you may not alter
 * the signature of the existing methods.
 */
public class ChessMove {

    private final ChessPosition startPosition;
    private final ChessPosition endPosition;
    private final ChessPiece.PieceType promotionPiece;

    /**
     * Constructor to initialize a chess move with pawn promotion.
     *
     * @param startPosition   The starting position of the move.
     * @param endPosition     The ending position of the move.
     * @param promotionPiece  The piece type to promote a pawn to, if applicable (can be null if no promotion).
     */
    public ChessMove(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.promotionPiece = promotionPiece;
    }

    /**
     * Constructor to initialize a chess move without promotion.
     *
     * @param startPosition   The starting position of the move.
     * @param endPosition     The ending position of the move.
     */
    public ChessMove(ChessPosition startPosition, ChessPosition endPosition) {
        this(startPosition, endPosition, null); // Calls the primary constructor with null for promotionPiece
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return this.startPosition;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return this.endPosition;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move.
     *
     * @return Type of piece to promote a pawn to, or null if no promotion.
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return this.promotionPiece;
    }
}
