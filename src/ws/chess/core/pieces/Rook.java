package ws.chess.core.pieces;

import ws.chess.core.Move;

import java.util.List;

public class Rook extends Piece {

    public Rook(Piece.Color color, int x, int y) {
        super(color, x, y);
    }

    public List<Move> getUniqueMovePattern() {
        return getLinearPattern();
    }

    String getSymbol() {
        return "R";
    }

    public Piece clone() {
        return new Rook(this.color, this.x, this.y);
    }
}
