package ws.chess.core.pieces;

import lombok.Getter;
import ws.chess.core.Move;

import java.util.ArrayList;
import java.util.List;

import static ws.chess.core.pieces.Piece.Color.WHITE;

public class Pawn extends Piece {
    @Getter
    private boolean isAttackPawn;
    private boolean hasMoved;
    @Getter
    private boolean ableToBeTakenEnPassant;

    public Pawn(Color color, int x, int y, boolean isAttackPawn, boolean hasMoved, boolean ableToBeTakenEnPassant) {
        super(color, x, y);
        this.isAttackPawn = isAttackPawn;
        this.hasMoved = hasMoved;
        this.ableToBeTakenEnPassant = ableToBeTakenEnPassant;
    }

    public List<Move> getUniqueMovePattern() {
        List<Move> moves = new ArrayList<>();
        int dir = this.getColor().equals(WHITE) ? 1 : -1;
        if (!hasMoved) moves.add(getMove(0, 2*dir,false, true));
        moves.add(getMove(0, dir, false, false));
        moves.add(getMove(1, dir, true, false));
        moves.add(getMove(-1, dir, true, false));
        return moves;
    }

    private Move getMove(int x, int y, boolean isAttackPawn, boolean ableToBeTakenEnPassant) {
        return Move.builder()
            .original(this)
            .destination(new Pawn(this.color, this.x + x, this.y + y, isAttackPawn, true, ableToBeTakenEnPassant))
            .build();
    }

    String getSymbol() {
        return "P";
    }

    public Piece clone() {
        return new Pawn(this.color, this.x, this.y, false, this.hasMoved, false);
    }
}
