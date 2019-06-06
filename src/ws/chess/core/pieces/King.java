package ws.chess.core.pieces;

import lombok.Getter;
import ws.chess.core.Board;
import ws.chess.core.Move;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    @Getter
    private boolean moved;
    private boolean castlingMove;

    public King(Color color, int x, int y, boolean moved, boolean castlingMove) {
        super(color, x, y);
        this.moved = moved;
        this.castlingMove = castlingMove;
    }

    public List<Move> getUniqueMovePattern() {
        List<Move> moves = new ArrayList<>();
        moves.add(move(1, 1));
        moves.add(move(1, 0));
        moves.add(move(1, -1));
        moves.add(move(0, 1));
        moves.add(move(0, 0));
        moves.add(move(0, -1));
        moves.add(move(-1, 1));
        moves.add(move(-1, 0));
        moves.add(move(-1, -1));

        if (!this.moved) {
            King clone1 = (King)this.clone();
            clone1.x = this.getX() + 2;
            clone1.castlingMove = true;
            King clone2 = (King)this.clone();
            clone2.x = this.getX() - 2;
            clone2.castlingMove = true;
            moves.add(Move.builder()
                .original(this)
                .destination(clone1)
                .build());
            moves.add(Move.builder()
                .original(this)
                .destination(clone2)
                .build());
        }
        return moves;
    }

    @Override
    boolean uniquePieceFilter(Move move, Board board){
        if (!castlingMove) return true;
        Piece rook;
        List<Move> movesToCheck = new ArrayList<>();

        // Setup relevant side
        if (move.getDestination().getY() == 6) {
            rook = board.getPiece(7, move.getOriginal().getY());
            movesToCheck.add(Move.builder()
                .original(move.getOriginal())
                .destination(new King(color, x, 5, true, false))
                .build());
        } else {
            rook = board.getPiece(0, move.getOriginal().getY());
            movesToCheck.add(Move.builder()
                .original(move.getOriginal())
                .destination(new King(color, x, 2, true, false))
                .build());
            movesToCheck.add(Move.builder()
                .original(move.getOriginal())
                .destination(new King(color, x, 3, true, false))
                .build());
        }
        return rook instanceof Rook && !((Rook)rook).isMoved() &&
            movesToCheck.stream().noneMatch(board::consumesKing);
    }

    public String getSymbol() {
        return "K";
    }

    public Piece clone() {
        return new King(this.color, this.x, this.y, this.moved, false);
    }
}
