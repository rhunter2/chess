package chess;

import java.util.Objects;

/**
 * Represents a chess move, including starting position, ending position, and
 * optional pawn promotion.
 * <p>
 * Note: The class is immutable, meaning its fields cannot be changed after initialization.
 */
public class ChessMove {

    private final ChessPosition startPosition;
    private final ChessPosition endPosition;
    private final ChessPiece.PieceType promotionPiece; // Nullable, only for pawn promotion

    /**
     * Constructs a ChessMove object representing a move from one position to another,
     * with an optional pawn promotion.
     *
     * @param startPosition   Starting position of the move
     * @param endPosition     Ending position of the move
     * @param promotionPiece  Type of piece for pawn promotion (nullable if no promotion)
     */
    public ChessMove(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        this.startPosition = Objects.requireNonNull(startPosition, "Start position cannot be null");
        this.endPosition = Objects.requireNonNull(endPosition, "End position cannot be null");
        this.promotionPiece = promotionPiece; // May be null for non-promotion moves
    }

    /**
     * @return The starting position of the move
     */
    public ChessPosition getStartPosition() {
        return startPosition;
    }

    /**
     * @return The ending position of the move
     */
    public ChessPosition getEndPosition() {
        return endPosition;
    }

    /**
     * @return The promotion piece type if it's a pawn promotion, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return promotionPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessMove)) return false;
        ChessMove other = (ChessMove) o;
        return startPosition.equals(other.startPosition) &&
                endPosition.equals(other.endPosition) &&
                Objects.equals(promotionPiece, other.promotionPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPosition, endPosition, promotionPiece);
    }

    @Override
    public String toString() {
        return String.format("ChessMove{start=%s, end=%s, promotion=%s}",
                startPosition, endPosition, promotionPiece != null ? promotionPiece : "None");
    }
}
