package ws.chess.core.pieces;

import ws.chess.core.Move;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color, int x, int y) {
        super(color, x, y);
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
        return moves;
    }

    public String getSymbol() {
        return "K";
    }

    public Piece clone() {
        return new King(this.getColor(), this.getX(), this.getY());
    }
}
