package ws.chess.core.pieces;

import lombok.Getter;
import lombok.Setter;
import ws.chess.core.Move;

import java.util.ArrayList;
import java.util.List;

import static ws.chess.core.pieces.Piece.Color.WHITE;

public class Pawn extends Piece {
    @Getter @Setter
    private boolean isAttackPawn;
    private boolean hasMoved;

    public Pawn(Color color, int x, int y, boolean isAttackPawn, boolean hasMoved) {
        super(color, x, y);
        this.isAttackPawn = isAttackPawn;
        this.hasMoved = hasMoved;
    }

    public List<Move> getUniqueMovePattern() {
        List<Move> moves = new ArrayList<>();
        int dir = this.getColor().equals(WHITE) ? 1 : -1;
        if (!hasMoved) moves.add(move(0,2 * dir));
        moves.add(move(0, dir));

        Pawn attack1 = (Pawn)this.clone();
        Pawn attack2 = (Pawn)this.clone();
        attack1.setAttackPawn(true);
        attack1.setX(attack1.getX() + 1);
        attack1.setY(attack1.getY() + dir);
        attack2.setAttackPawn(true);
        attack2.setX(attack2.getX() - 1);
        attack2.setY(attack2.getY() + dir);

        moves.add(Move.builder()
            .original(this)
            .destination(attack1)
            .build()
        );

        moves.add(Move.builder()
            .original(this)
            .destination(attack2)
            .build()
        );

        return moves;
    }

    String getSymbol() {
        return "P";
    }

    public Piece clone() {
        return new Pawn(this.getColor(), this.getX(), this.getY(), false, true);
    }
}
