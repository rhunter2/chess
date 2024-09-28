package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * Represents a single chess piece
 * Note: You can add to this class, but you may not alter
 * the signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;

    public Collection<ChessMove> getValidMoves(ChessBoard board) {
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> validMoves = new HashSet<>();

        switch (this.type) {
            case KING:
                addKingMoves(board, myPosition, validMoves);
                break;
            case QUEEN:
                addRookMoves(board, myPosition, validMoves);
                addBishopMoves(board, myPosition, validMoves);
                break;
            case ROOK:
                addRookMoves(board, myPosition, validMoves);
                break;
            case BISHOP:
                addBishopMoves(board, myPosition, validMoves);
                break;
            case KNIGHT:
                addKnightMoves(board, myPosition, validMoves);
                break;
            case PAWN:
                addPawnMoves(board, myPosition, validMoves);
                break;
        }

        return validMoves;
    }

    // King moves 1 square in any direction
    private void addKingMoves(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> validMoves) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] direction : directions) {
            ChessPosition newPosition = myPosition.offset(direction[0], direction[1]);
            if (board.isValidPosition(newPosition) && (board.getPieceAt(newPosition) == null || board.getPieceAt(newPosition).getTeamColor() != this.pieceColor)) {
                validMoves.add(new ChessMove(myPosition, newPosition));
            }
        }
    }

    // Rook moves in straight lines (horizontal and vertical)
    private void addRookMoves(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> validMoves) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        addSlidingPieceMoves(board, myPosition, directions, validMoves);
    }

    // Bishop moves diagonally
    private void addBishopMoves(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> validMoves) {
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        addSlidingPieceMoves(board, myPosition, directions, validMoves);
    }

    // Queen combines rook and bishop movement
    // This is handled by calling both `addRookMoves` and `addBishopMoves` in `pieceMoves`

    // Knight moves in "L" shapes
    private void addKnightMoves(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> validMoves) {
        int[][] knightOffsets = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        for (int[] offset : knightOffsets) {
            ChessPosition newPosition = myPosition.offset(offset[0], offset[1]);
            if (board.isValidPosition(newPosition) && (board.getPieceAt(newPosition) == null || board.getPieceAt(newPosition).getTeamColor() != this.pieceColor)) {
                validMoves.add(new ChessMove(myPosition, newPosition));
            }
        }
    }

    // Pawn moves forward 1 square, but captures diagonally
    private void addPawnMoves(ChessBoard board, ChessPosition myPosition, Collection<ChessMove> validMoves) {
        int direction = (this.pieceColor == ChessGame.TeamColor.WHITE) ? 1 : -1; // White moves up, Black moves down
        ChessPosition oneStep = myPosition.offset(0, direction);

        // Move forward 1 square if empty
        if (board.isValidPosition(oneStep) && board.getPieceAt(oneStep) == null) {
            validMoves.add(new ChessMove(myPosition, oneStep));
        }

        // Capture diagonally
        int[][] captureOffsets = {{-1, direction}, {1, direction}};
        for (int[] offset : captureOffsets) {
            ChessPosition newPosition = myPosition.offset(offset[0], offset[1]);
            if (board.isValidPosition(newPosition) && board.getPieceAt(newPosition) != null && board.getPieceAt(newPosition).getTeamColor() != this.pieceColor) {
                validMoves.add(new ChessMove(myPosition, newPosition));
            }
        }

        // Initial double move
        if ((this.pieceColor == ChessGame.TeamColor.WHITE && myPosition.getRow() == 1) || (this.pieceColor == ChessGame.TeamColor.BLACK && myPosition.getRow() == 6)) {
            ChessPosition twoSteps = myPosition.offset(0, 2 * direction);
            if (board.isValidPosition(twoSteps) && board.getPieceAt(oneStep) == null && board.getPieceAt(twoSteps) == null) {
                validMoves.add(new ChessMove(myPosition, twoSteps));
            }
        }
    }

    // Handles sliding pieces like rooks and bishops (and queens, since they move like both)
    private void addSlidingPieceMoves(ChessBoard board, ChessPosition myPosition, int[][] directions, Collection<ChessMove> validMoves) {
        for (int[] direction : directions) {
            ChessPosition newPosition = myPosition.offset(direction[0], direction[1]);
            while (board.isValidPosition(newPosition)) {
                ChessPiece targetPiece = board.getPieceAt(newPosition);
                if (targetPiece == null) {
                    validMoves.add(new ChessMove(myPosition, newPosition)); // Empty square
                } else {
                    if (targetPiece.getTeamColor() != this.pieceColor) {
                        validMoves.add(new ChessMove(myPosition, newPosition)); // Capture opponent's piece
                    }
                    break; // Stop if a piece is in the way
                }
                newPosition = newPosition.offset(direction[0], direction[1]); // Continue sliding
            }
        }
    }
}

