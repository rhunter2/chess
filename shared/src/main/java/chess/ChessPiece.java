package chess;
import chess.validmovescalculator.*;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;


/**
 * Represents a single chess piece on the chessboard.
 * This class is immutable, ensuring that the piece's color and type cannot be changed after creation.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;
    private static final Map<PieceType, PieceMovesCalculator> validCalculators = createCalculatorMap();

    /**
     * Constructor for a chess piece.
     *
     * @param pieceColor The color of the piece (TeamColor.WHITE or TeamColor.BLACK).
     * @param type       The type of the chess piece (e.g., KING, QUEEN, etc.).
     */
    public ChessPiece(ChessGame.TeamColor pieceColor, PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece types.
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * Creates and returns a mapping between PieceType and the corresponding PieceMovesCalculator.
     *
     * @return A Map of PieceType to PieceMovesCalculator.
     */
    private static Map<PieceType, PieceMovesCalculator> createCalculatorMap() {
        Map<PieceType, PieceMovesCalculator> map = new EnumMap<>(PieceType.class);
        map.put(PieceType.BISHOP, new BishopValidMovesCalculator());
        map.put(PieceType.KING, new KingValidMovesCalculator());
        map.put(PieceType.KNIGHT, new KnightValidMovesCalculator());
        map.put(PieceType.PAWN, new PawnValidMovesCalculator());
        map.put(PieceType.QUEEN, new QueenValidMovesCalculator());
        map.put(PieceType.ROOK, new RookValidMovesCalculator());
        return map;
    }

    /**
     * @return The color (team) this piece belongs to.
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return The type of chess piece (e.g., KING, QUEEN, etc.).
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions this chess piece can move to, without considering moves
     * that might leave the king in check.
     *
     * @param board       The chessboard on which the piece is currently located.
     * @param myPosition  The current position of the piece.
     * @return A collection of valid moves for the piece.
     */

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        PieceMovesCalculator calculator;
        PieceType type=this.getPieceType();

        // Use switch statement to get the correct calculator based on piece type
        switch (type) {
            case KING:
                calculator = new KingValidMovesCalculator();
                break;
            case QUEEN:
                calculator = new QueenValidMovesCalculator();
                break;
            case ROOK:
                calculator = new RookValidMovesCalculator();
                break;
            case BISHOP:
                calculator = new BishopValidMovesCalculator();
                break;
            case KNIGHT:
                calculator = new KnightValidMovesCalculator();
                break;
            case PAWN:
                calculator = new PawnValidMovesCalculator();
                break;
            default:
                return new HashSet<>(); // Return an empty set if the piece type is unrecognized
        }

        // Calculate and return the valid moves
        return calculator.pieceMoves(board, myPosition);
    }

    @Override
    public String toString() {
        return String.format("ChessPiece{color=%s, type=%s}", pieceColor, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPiece)) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
}