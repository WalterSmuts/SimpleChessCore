package ws.chess.core.pieces;

import lombok.Getter;
import ws.chess.core.Move;

import java.util.List;

public class Rook extends Piece {
    @Getter
    private boolean moved;

    public Rook(Piece.Color color, int x, int y, boolean moved) {
        super(color, x, y);
        this.moved = moved;
    }

    public List<Move> getUniqueMovePattern() {
        return getLinearPattern();
    }

    String getSymbol() {
        return "R";
    }

    public Piece clone() {
        return new Rook(this.color, this.x, this.y, this.moved);
    }
}
