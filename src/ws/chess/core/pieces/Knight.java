package ws.chess.core.pieces;

import ws.chess.core.Move;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Piece.Color color, int x, int y) {
        super(color, x, y);
    }

    public List<Move> getUniqueMovePattern() {
        List<Move> moves = new ArrayList<>();
        moves.add(move(+2, +1));
        moves.add(move(+2, -1));
        moves.add(move(+1, +2));
        moves.add(move(+1, -2));
        moves.add(move(-2, +1));
        moves.add(move(-2, -1));
        moves.add(move(-1, +2));
        moves.add(move(-1, -2));
        return moves;
    }

    String getSymbol() {
        return "H";
    }

    public Piece clone() {
        return new Knight(this.color, this.x, this.y);
    }
}
